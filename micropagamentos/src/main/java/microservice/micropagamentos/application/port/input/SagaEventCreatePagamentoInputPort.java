package microservice.micropagamentos.application.port.input;

import microservice.micropagamentos.application.core.domain.SagaEvent;

public interface SagaEventCreatePagamentoInputPort {

    SagaEvent realizePayment(SagaEvent sagaEvent);
}

