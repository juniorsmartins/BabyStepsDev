package microservice.microtimes.application.port.output;

import microservice.microtimes.application.core.domain.ValidationModel;

public interface SagaEventSaveSuccessValidationOutputPort {

    ValidationModel saveSuccessValidation(ValidationModel validationModel);
}

