package microservice.micropagamentos.application.port.input;

import microservice.micropagamentos.application.core.domain.SagaEvent;

public interface SagaEventPagamentoInputPort {

    SagaEvent realizePayment(SagaEvent sagaEvent);
}

