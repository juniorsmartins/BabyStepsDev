package microservice.micropagamentos.application.core.usecase;

import microservice.micropagamentos.adapter.mapper.MapperIn;
import microservice.micropagamentos.adapter.utils.JsonUtil;
import microservice.micropagamentos.application.core.domain.Pagamento;
import microservice.micropagamentos.application.core.domain.SagaEvent;
import microservice.micropagamentos.application.core.domain.enums.EPagamentoStatus;
import microservice.micropagamentos.application.port.input.SagaEventCreatePagamentoInputPort;
import microservice.micropagamentos.application.port.output.SagaEventExistsOutputPort;
import microservice.micropagamentos.application.port.output.SagaEventSavePagamentoOutputPort;
import microservice.micropagamentos.application.port.output.SagaEventSendOrchestratorOutputPot;
import microservice.micropagamentos.config.exception.http_409.SagaEventNullValueNotAllowedException;
import microservice.micropagamentos.config.exception.http_409.SagaEventPagamentoDuplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

public class SagaEventCreatePagamentoUseCase implements SagaEventCreatePagamentoInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventCreatePagamentoUseCase.class);

    private static final String CURRENT_SOURCE = "PAGAMENTO-SERVICE";

    private final SagaEventSavePagamentoOutputPort sagaEventSavePagamentoOutputPort;

    private final SagaEventSendOrchestratorOutputPot sagaEventSendOrchestratorOutputPot;

    private final SagaEventExistsOutputPort sagaEventExistsOutputPort;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventCreatePagamentoUseCase(SagaEventSavePagamentoOutputPort sagaEventSavePagamentoOutputPort,
                                           SagaEventSendOrchestratorOutputPot sagaEventSendOrchestratorOutputPot,
                                           SagaEventExistsOutputPort sagaEventExistsOutputPort,
            MapperIn mapperIn,
            JsonUtil jsonUtil
    ) {
        this.sagaEventSavePagamentoOutputPort = sagaEventSavePagamentoOutputPort;
        this.sagaEventSendOrchestratorOutputPot = sagaEventSendOrchestratorOutputPot;
        this.sagaEventExistsOutputPort = sagaEventExistsOutputPort;
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public SagaEvent realizePayment(SagaEvent sagaEvent) {

        log.info("Iniciado serviço para criar Pagamento-Service.");

        var pagamentoCreated = Optional.ofNullable(sagaEvent)
            .map(event -> {
                try {
                    this.checkExistenceMandatoryValues(event);
                    this.checkExistenceValidationDuplication(event);
                    var pagamento = this.createPendingPagamento(event);
                    this.sagaEventSavePagamentoOutputPort.save(pagamento);

                } catch (SagaEventNullValueNotAllowedException | SagaEventPagamentoDuplicationException ex) {
                    log.error("Erro: {}", ex.getMessage(), ex);

                }

                this.sagaEventSendOrchestratorOutputPot.sendEvent(this.jsonUtil.toJson(event));
                return event;
            })
            .orElseThrow();

        log.info("Finalizado serviço para criar Pagamento-Service: {}.", pagamentoCreated);

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
}

