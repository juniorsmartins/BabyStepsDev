package microservice.micropagamentos.application.core.usecase;

import microservice.micropagamentos.adapter.mapper.MapperIn;
import microservice.micropagamentos.adapter.utils.JsonUtil;
import microservice.micropagamentos.application.core.domain.History;
import microservice.micropagamentos.application.core.domain.SagaEvent;
import microservice.micropagamentos.application.core.domain.enums.EEventSource;
import microservice.micropagamentos.application.core.domain.enums.ESagaStatus;
import microservice.micropagamentos.application.port.input.SagaEventFailInputPort;
import microservice.micropagamentos.application.port.output.PagamentoDeleteOutputPort;
import microservice.micropagamentos.application.port.output.PagamentoFindOutputPort;
import microservice.micropagamentos.application.port.output.CarteiroNotifyOrchestratorOutputPort;
import microservice.micropagamentos.config.exception.http.SagaEventNullValueException;
import microservice.micropagamentos.config.exception.http_404.PagamentoNotFoundException;
import microservice.micropagamentos.config.exception.http_500.NullValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.Optional;

public class SagaEventFailUseCase implements SagaEventFailInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventFailUseCase.class);

    private final PagamentoFindOutputPort pagamentoFindOutputPort;

    private final PagamentoDeleteOutputPort pagamentoDeleteOutputPort;

    private final CarteiroNotifyOrchestratorOutputPort sagaEventOrchestratorOutputPort;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventFailUseCase(
            PagamentoFindOutputPort pagamentoFindOutputPort,
            PagamentoDeleteOutputPort pagamentoDeleteOutputPort,
            CarteiroNotifyOrchestratorOutputPort sagaEventOrchestratorOutputPort,
            MapperIn mapperIn,
            JsonUtil jsonUtil
    ) {
        this.pagamentoFindOutputPort = pagamentoFindOutputPort;
        this.pagamentoDeleteOutputPort = pagamentoDeleteOutputPort;
        this.sagaEventOrchestratorOutputPort = sagaEventOrchestratorOutputPort;
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public SagaEvent realizeRefund(SagaEvent event) {

        log.info("Serviço iniciado para rollback de Pagamento.");

        var sagaEventConclusion = Optional.ofNullable(event)
            .map(this::sagaProcessFail)
            .map(this::sagaResponseOrchestrator)
            .orElseThrow();

        log.info("Serviço finalizado para rollback de Pagamento: {}", sagaEventConclusion);

        return sagaEventConclusion;
    }

    private SagaEvent sagaProcessFail(SagaEvent event) {
        try {
            this.checkExistenceTimeIdAndTorneioId(event);
            this.rollbackPagamento(event);
            this.handleSuccessFail(event);

        } catch (SagaEventNullValueException | PagamentoNotFoundException ex) {
            log.error("Erro: {}", ex.getMessage(), ex);
            this.handleRollbackPending(event, ex.getMessage());
        }
        return event;
    }

    private void checkExistenceTimeIdAndTorneioId(SagaEvent event) {
        if (ObjectUtils.isEmpty(event.getTimeId()) || ObjectUtils.isEmpty(event.getTorneioId())) {
            throw new SagaEventNullValueException();
        }
    }

    private void rollbackPagamento(SagaEvent sagaEvent) {

        Optional.ofNullable(sagaEvent)
            .ifPresentOrElse(event -> {
                var pagamento = this.pagamentoFindOutputPort
                    .findByTorneioIdAndTimeId(event.getTorneioId(), event.getTimeId());
                this.pagamentoDeleteOutputPort.deleteById(pagamento.getId());
            },
            () -> {throw new NullValueException();}
        );
    }

    private void handleSuccessFail(SagaEvent event) {
        event.setStatus(ESagaStatus.FAIL);
        event.setSource(EEventSource.PAGAMENTO_SERVICE);
        this.addHistory(event, "Rollback bem-sucedido ao remover operação de Pagamento.");
    }

    private void addHistory(SagaEvent event, String message) {
        var history = new History();
        history.setMessage(message);
        history.setSource(event.getSource());
        history.setStatus(event.getStatus());
        history.setCreatedAt(OffsetDateTime.now());

        event.addToHistory(history);
    }

    private void handleRollbackPending(SagaEvent event, String message) {
        event.setStatus(ESagaStatus.ROLLBACK_PENDING);
        event.setSource(EEventSource.PAGAMENTO_SERVICE);
        this.addHistory(event, "Rollback falha ao remover operação de Pagamento: ".concat(message));
    }

    private SagaEvent sagaResponseOrchestrator(SagaEvent event) {
        var sagaEventRequest = this.mapperIn.toSagaEventRequest(event);
        var payload = this.jsonUtil.toJson(sagaEventRequest);
        this.sagaEventOrchestratorOutputPort.sendEvent(payload);
        return event;
    }

}

