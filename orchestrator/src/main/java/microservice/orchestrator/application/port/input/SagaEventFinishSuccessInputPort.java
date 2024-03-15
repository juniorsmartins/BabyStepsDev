package microservice.orchestrator.application.port.input;

import microservice.orchestrator.application.core.domain.SagaEvent;

public interface SagaEventFinishSuccessInputPort {

    SagaEvent finishSagaSuccess(SagaEvent event);

}

