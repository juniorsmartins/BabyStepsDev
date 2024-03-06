package microservice.microtimes.application.port.input;

import microservice.microtimes.application.core.domain.SagaEvent;
import microservice.microtimes.application.core.domain.ValidationModel;

public interface SagaEventSuccessValidationInputPort {

    ValidationModel createSuccessValidation(SagaEvent sagaEvent);
}

