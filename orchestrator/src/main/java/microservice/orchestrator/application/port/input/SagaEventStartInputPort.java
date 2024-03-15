package microservice.orchestrator.application.port.input;

import microservice.orchestrator.application.core.domain.SagaEvent;

public interface SagaEventStartInputPort {

    SagaEvent startSaga(SagaEvent event);

}

