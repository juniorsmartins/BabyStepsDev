package microservice.microtimes.application.port.input;

import microservice.microtimes.application.core.domain.SagaEvent;

public interface SagaEventSuccessValidationInputPort {

    SagaEvent successValidation(SagaEvent sagaEvent);
}

