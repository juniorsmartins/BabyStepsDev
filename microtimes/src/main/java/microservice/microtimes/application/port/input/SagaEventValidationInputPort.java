package microservice.microtimes.application.port.input;

import microservice.microtimes.application.core.domain.SagaEvent;

public interface SagaEventValidationInputPort {

    void createValidation(SagaEvent sagaEvent);

    void rollbackEvent(SagaEvent event);
}

