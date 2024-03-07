package microservice.micropagamentos.application.core.usecase;

import microservice.micropagamentos.adapter.mapper.MapperIn;
import microservice.micropagamentos.adapter.utils.JsonUtil;
import microservice.micropagamentos.application.core.domain.SagaEvent;
import microservice.micropagamentos.application.port.input.SagaEventCreatePagamentoInputPort;
import microservice.micropagamentos.application.port.output.SagaEventSavePagamentoOutputPort;
import microservice.micropagamentos.application.port.output.SagaEventSendOrchestratorOutputPot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SagaEventCreatePagamentoUseCase implements SagaEventCreatePagamentoInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventCreatePagamentoUseCase.class);

    private static final String CURRENT_SOURCE = "PAGAMENTO-SERVICE";

    private final SagaEventSavePagamentoOutputPort sagaEventSavePagamentoOutputPort;

    private final SagaEventSendOrchestratorOutputPot sagaEventSendOrchestratorOutputPot;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventCreatePagamentoUseCase(SagaEventSavePagamentoOutputPort sagaEventSavePagamentoOutputPort,
                                           SagaEventSendOrchestratorOutputPot sagaEventSendOrchestratorOutputPot,
            MapperIn mapperIn,
            JsonUtil jsonUtil
    ) {
        this.sagaEventSavePagamentoOutputPort = sagaEventSavePagamentoOutputPort;
        this.sagaEventSendOrchestratorOutputPot = sagaEventSendOrchestratorOutputPot;
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public SagaEvent realizePayment(SagaEvent sagaEvent) {

        try {


        } catch () {
            log.error("Erro: {}", ex.getMessage(), ex);
        }
        return null;
    }
}

