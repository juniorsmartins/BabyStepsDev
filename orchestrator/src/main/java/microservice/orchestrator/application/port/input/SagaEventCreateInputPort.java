package microservice.orchestrator.application.port.input;

import microservice.orchestrator.application.core.domain.SagaEvent;

public interface SagaEventCreateInputPort {

    SagaEvent create(SagaEvent sagaEvent);

}

