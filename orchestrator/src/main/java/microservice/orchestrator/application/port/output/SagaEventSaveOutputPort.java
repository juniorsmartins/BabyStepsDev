package microservice.orchestrator.application.port.output;

import microservice.orchestrator.application.core.domain.SagaEvent;

public interface SagaEventSaveOutputPort {

    SagaEvent create(SagaEvent sagaEvent);

}

