package microservice.micropagamentos.application.core.usecase;

import microservice.micropagamentos.adapter.mapper.MapperIn;
import microservice.micropagamentos.adapter.utils.JsonUtil;
import microservice.micropagamentos.application.core.domain.History;
import microservice.micropagamentos.application.core.domain.Pagamento;
import microservice.micropagamentos.application.core.domain.SagaEvent;
import microservice.micropagamentos.application.core.domain.enums.EEventSource;
import microservice.micropagamentos.application.core.domain.enums.EPagamentoStatus;
import microservice.micropagamentos.application.core.domain.enums.ESagaStatus;
import microservice.micropagamentos.application.port.input.SagaEventSuccessInputPort;
import microservice.micropagamentos.application.port.output.PagamentoExistsOutputPort;
import microservice.micropagamentos.application.port.output.PagamentoFindOutputPort;
import microservice.micropagamentos.application.port.output.PagamentoSaveOutputPort;
import microservice.micropagamentos.application.port.output.CarteiroNotifyOrchestratorOutputPort;
import microservice.micropagamentos.config.exception.http.SagaEventDuplicateException;
import microservice.micropagamentos.config.exception.http.SagaEventNullValueException;
import microservice.micropagamentos.config.exception.http_404.PagamentoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.Optional;

public class SagaEventSuccessUseCase implements SagaEventSuccessInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventSuccessUseCase.class);

    private final PagamentoExistsOutputPort pagamentoExistsOutputPort;

    private final PagamentoSaveOutputPort pagamentoSaveOutputPort;

    private final PagamentoFindOutputPort pagamentoFindOutputPort;

    private final CarteiroNotifyOrchestratorOutputPort sagaEventOrchestratorOutputPort;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventSuccessUseCase(
            PagamentoExistsOutputPort pagamentoExistsOutputPort,
            PagamentoSaveOutputPort pagamentoSaveOutputPort,
            PagamentoFindOutputPort pagamentoFindOutputPort,
            CarteiroNotifyOrchestratorOutputPort sagaEventOrchestratorOutputPort,
            MapperIn mapperIn,
            JsonUtil jsonUtil
    ) {
        this.pagamentoExistsOutputPort = pagamentoExistsOutputPort;
        this.pagamentoSaveOutputPort = pagamentoSaveOutputPort;
        this.pagamentoFindOutputPort = pagamentoFindOutputPort;
        this.sagaEventOrchestratorOutputPort = sagaEventOrchestratorOutputPort;
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public SagaEvent realizePayment(SagaEvent sagaEvent) {

        log.info("Serviço iniciado para efetuar Pagamento.");

        var pagamentoCreated = Optional.ofNullable(sagaEvent)
            .map(this::sagaProcessSuccess)
            .map(this::sagaResponseOrchestrator)
            .orElseThrow();

        log.info("Serviço finalizado para efetuar Pagamento: {}.", pagamentoCreated);

        return sagaEvent;
    }

    private SagaEvent sagaProcessSuccess(SagaEvent event) {
        try {
            this.checkExistenceTimeIdAndTorneioId(event);
            this.checkExistencePaymentData(event);
            this.checkExistencePaymentDuplicate(event);
            var pagamento = this.createPendingPagamento(event);
            this.pagamentoSaveOutputPort.save(pagamento);

            var pagamentoEncontrado = this.pagamentoFindOutputPort.findByTorneioIdAndTimeId(event.getTorneioId(), event.getTimeId());
            this.changePagamentoToSuccess(pagamentoEncontrado);
            this.pagamentoSaveOutputPort.save(pagamentoEncontrado);
            this.handleSuccess(event);

        } catch (SagaEventNullValueException | SagaEventDuplicateException | PagamentoNotFoundException ex) {
            log.error("Erro: {}", ex.getMessage(), ex);
            this.handleFail(event, ex.getMessage());
        }

        return event;
    }

    private void checkExistenceTimeIdAndTorneioId(SagaEvent event) {
        if (ObjectUtils.isEmpty(event.getTimeId()) || ObjectUtils.isEmpty(event.getTorneioId())) {
            throw new SagaEventNullValueException();
        }
    }

    private void checkExistencePaymentData(SagaEvent event) {
        var payload = event.getPayload();

        if (ObjectUtils.isEmpty(payload.getNumeroBanco()) || ObjectUtils.isEmpty(payload.getNumeroAgencia()) ||
                ObjectUtils.isEmpty(payload.getNumeroCartao()) || ObjectUtils.isEmpty(payload.getTipo())) {
            throw new SagaEventNullValueException();
        }
    }

    private void checkExistencePaymentDuplicate(SagaEvent event) {
        var torneioId = event.getTorneioId();
        var timeId = event.getTimeId();

        var exists = this.pagamentoExistsOutputPort.existsDuplicate(torneioId, timeId);
        if (exists) {
            throw new SagaEventDuplicateException(torneioId, timeId);
        }
    }

    private Pagamento createPendingPagamento(SagaEvent event) {
        var pagamento = new Pagamento();
        pagamento.setSagaEventId(event.getSagaEventId());
        pagamento.setTransactionId(event.getTransactionId());
        pagamento.setTorneioId(event.getTorneioId());
        pagamento.setTimeId(event.getTimeId());
        pagamento.setNumeroAgencia(event.getPayload().getNumeroAgencia());
        pagamento.setNumeroBanco(event.getPayload().getNumeroBanco());
        pagamento.setNumeroCartao(event.getPayload().getNumeroCartao());
        pagamento.setTipo(event.getPayload().getTipo());
        pagamento.setStatus(EPagamentoStatus.PENDING);

        return pagamento;
    }

    private void changePagamentoToSuccess(Pagamento pagamento) {
        pagamento.setStatus(EPagamentoStatus.SUCCESS);
    }

    private void handleSuccess(SagaEvent event) {
        event.setStatus(ESagaStatus.SUCCESS);
        event.setSource(EEventSource.PAGAMENTO_SERVICE);
        addHistory(event, "Pagamento bem-sucedido!");
    }

    private void addHistory(SagaEvent event, String message) {
        var history = new History();
        history.setMessage(message);
        history.setSource(event.getSource());
        history.setStatus(event.getStatus());
        history.setCreatedAt(OffsetDateTime.now());

        event.addToHistory(history);
    }

    private void handleFail(SagaEvent event, String message) {
        event.setStatus(ESagaStatus.ROLLBACK_PENDING);
        event.setSource(EEventSource.PAGAMENTO_SERVICE);
        this.addHistory(event, "Falha ao realizar pagamento: ".concat(message));
    }

    private SagaEvent sagaResponseOrchestrator(SagaEvent event) {
        var sagaEventRequest = this.mapperIn.toSagaEventRequest(event);
        var payload = this.jsonUtil.toJson(sagaEventRequest);
        this.sagaEventOrchestratorOutputPort.sendEvent(payload);
        return event;
    }

}

