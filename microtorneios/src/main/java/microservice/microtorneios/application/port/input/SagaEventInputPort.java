package microservice.microtorneios.application.port.input;

import microservice.microtorneios.application.core.domain.SagaEvent;

public interface SagaEventInputPort {

    SagaEvent createValidation(SagaEvent sagaEvent);

    SagaEvent rollbackEvent(SagaEvent event);
}

