package microservice.orchestrator.application.port.input;

import microservice.orchestrator.application.core.domain.SagaEvent;

public interface SagaEventFinishFailInputPort {

    SagaEvent finishFail(SagaEvent event);

}

