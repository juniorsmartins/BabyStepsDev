package microservice.microtimes.application.port.input;

import microservice.microtimes.application.core.domain.SagaEvent;

public interface SagaEventInputPort {

    SagaEvent createValidation(SagaEvent sagaEvent);

    SagaEvent rollbackEvent(SagaEvent event);
}

