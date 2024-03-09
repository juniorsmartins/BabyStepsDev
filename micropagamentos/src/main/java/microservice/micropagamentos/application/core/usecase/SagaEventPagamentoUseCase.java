package microservice.micropagamentos.application.core.usecase;

import microservice.micropagamentos.adapter.mapper.MapperIn;
import microservice.micropagamentos.adapter.utils.JsonUtil;
import microservice.micropagamentos.application.core.domain.History;
import microservice.micropagamentos.application.core.domain.Pagamento;
import microservice.micropagamentos.application.core.domain.SagaEvent;
import microservice.micropagamentos.application.core.domain.enums.EPagamentoStatus;
import microservice.micropagamentos.application.core.domain.enums.ESagaStatus;
import microservice.micropagamentos.application.port.input.SagaEventPagamentoInputPort;
import microservice.micropagamentos.application.port.output.SagaEventExistsOutputPort;
import microservice.micropagamentos.application.port.output.SagaEventFindOutputPort;
import microservice.micropagamentos.application.port.output.SagaEventSavePagamentoOutputPort;
import microservice.micropagamentos.application.port.output.SagaEventSendOrchestratorOutputPot;
import microservice.micropagamentos.config.exception.http_404.SagaEventNotFoundException;
import microservice.micropagamentos.config.exception.http_409.SagaEventNullValueNotAllowedException;
import microservice.micropagamentos.config.exception.http_409.SagaEventPagamentoDuplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.Optional;

public class SagaEventPagamentoUseCase implements SagaEventPagamentoInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventPagamentoUseCase.class);

    private static final String CURRENT_SOURCE = "PAGAMENTO-SERVICE";

    private final SagaEventSavePagamentoOutputPort sagaEventSavePagamentoOutputPort;

    private final SagaEventSendOrchestratorOutputPot sagaEventSendOrchestratorOutputPot;

    private final SagaEventExistsOutputPort sagaEventExistsOutputPort;

    private final SagaEventFindOutputPort sagaEventFindOutputPort;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventPagamentoUseCase(SagaEventSavePagamentoOutputPort sagaEventSavePagamentoOutputPort,
                                     SagaEventSendOrchestratorOutputPot sagaEventSendOrchestratorOutputPot,
                                     SagaEventExistsOutputPort sagaEventExistsOutputPort,
                                     SagaEventFindOutputPort sagaEventFindOutputPort,
                                     MapperIn mapperIn,
                                     JsonUtil jsonUtil
    ) {
        this.sagaEventSavePagamentoOutputPort = sagaEventSavePagamentoOutputPort;
        this.sagaEventSendOrchestratorOutputPot = sagaEventSendOrchestratorOutputPot;
        this.sagaEventExistsOutputPort = sagaEventExistsOutputPort;
        this.sagaEventFindOutputPort = sagaEventFindOutputPort;
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public SagaEvent realizePayment(SagaEvent sagaEvent) {

        log.info("Iniciado serviço para criar Pagamento.");

        var pagamentoCreated = Optional.ofNullable(sagaEvent)
            .map(event -> {
                try {
                    this.checkExistenceMandatoryValues(event);
                    this.checkExistenceValidationDuplication(event);
                    var pagamento = this.createPendingPagamento(event);
                    this.sagaEventSavePagamentoOutputPort.save(pagamento);
                    var pagamentoFind = this.findBySagaEventIdAndTransactionId(event);
                    this.changePagamentoToSuccess(pagamentoFind);
                    this.handleSuccess(event);

                } catch (SagaEventNullValueNotAllowedException | SagaEventPagamentoDuplicationException |
                        SagaEventNotFoundException ex) {
                    log.error("Erro: {}", ex.getMessage(), ex);
                    this.handleFailCurrentNotExecuted(sagaEvent, ex.getMessage());

                }

                this.sagaEventSendOrchestratorOutputPot.sendEvent(this.jsonUtil.toJson(event));
                return event;
            })
            .orElseThrow();

        log.info("Finalizado serviço para criar Pagamento: {}.", pagamentoCreated);

        return sagaEvent;
    }

    private void checkExistenceMandatoryValues(SagaEvent event) {
        if (ObjectUtils.isEmpty(event.getSagaEventId()) || ObjectUtils.isEmpty(event.getTransactionId())
                || ObjectUtils.isEmpty(event.getPayload())) {
            throw new SagaEventNullValueNotAllowedException();
        }
    }

    private void checkExistenceValidationDuplication(SagaEvent event) {
        var exists = this.sagaEventExistsOutputPort.existsDuplication(event.getSagaEventId(), event.getTransactionId());
        if (exists) {
            throw new SagaEventPagamentoDuplicationException();
        }
    }

    private Pagamento createPendingPagamento(SagaEvent event) {
        var pagamento = new Pagamento();
        pagamento.setSagaEventId(event.getSagaEventId());
        pagamento.setTransactionId(event.getTransactionId());
        pagamento.setNumeroAgencia(event.getPayload().getNumeroAgencia());
        pagamento.setNumeroBanco(event.getPayload().getNumeroBanco());
        pagamento.setNumeroCartao(event.getPayload().getNumeroCartao());
        pagamento.setTipo(event.getPayload().getTipo());
        pagamento.setStatus(EPagamentoStatus.PENDING);

        return pagamento;
    }

    private void changePagamentoToSuccess(Pagamento pagamento) {
        pagamento.setStatus(EPagamentoStatus.SUCCESS);
        this.sagaEventSavePagamentoOutputPort.save(pagamento);
    }

    private void handleSuccess(SagaEvent event) {
        event.setStatus(ESagaStatus.SUCCESS);
        event.setSource(CURRENT_SOURCE);
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

    private void handleFailCurrentNotExecuted(SagaEvent event, String message) {
        event.setStatus(ESagaStatus.ROLLBACK_PENDING);
        event.setSource(CURRENT_SOURCE);
        this.addHistory(event, "Falha ao realizar pagamento: ".concat(message));
    }

    @Override
    public SagaEvent realizeRefund(SagaEvent event) {

        log.info("Iniciado serviço para rollback de Pagamento.");

        event.setStatus(ESagaStatus.FAIL);
        event.setSource(CURRENT_SOURCE);

        try {
            this.changePagamentoStatusToRefund(event);
            addHistory(event, "Rollback executado na operação de pagamento.");

        } catch (SagaEventNotFoundException ex) {
            addHistory(event, "Rollback não executado na operação de pagamento: ".concat(ex.getMessage()));
        }

        this.sagaEventSendOrchestratorOutputPot.sendEvent(this.jsonUtil.toJson(event));

        log.info("Finalizado serviço para rollback de Pagamento: {}.", event);

        return event;
    }

    private void changePagamentoStatusToRefund(SagaEvent event) {
        var pagamento = this.findBySagaEventIdAndTransactionId(event);
        pagamento.setStatus(EPagamentoStatus.REFUND);
        this.sagaEventSavePagamentoOutputPort.save(pagamento);
    }

    private Pagamento findBySagaEventIdAndTransactionId(SagaEvent event) {
        return this.sagaEventFindOutputPort.findBySagaEventIdAndTransactionId(event.getSagaEventId(), event.getTransactionId())
            .orElseThrow(SagaEventNotFoundException::new);
    }
}

