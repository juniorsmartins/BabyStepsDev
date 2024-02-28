package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.SagaEvent;

public interface StartSagaProducerOutputPort {

    void sendEvent(SagaEvent sagaEvent);
}

