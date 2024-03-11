package microservice.microtimes.application.port.output;

import microservice.microtimes.application.core.domain.ValidationModel;

public interface SagaEventSaveOutputPort {

    ValidationModel save(ValidationModel validationModel);
}

