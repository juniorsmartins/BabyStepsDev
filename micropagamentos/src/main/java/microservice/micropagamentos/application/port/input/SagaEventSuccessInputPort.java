package microservice.micropagamentos.application.port.input;

import microservice.micropagamentos.application.core.domain.SagaEvent;

public interface SagaEventSuccessInputPort {

    SagaEvent realizePayment(SagaEvent sagaEvent);

}

