package microservice.orchestrator.application.port.output;

import microservice.orchestrator.application.core.domain.SagaEvent;

public interface CarteiroNotifyTopicOutputPort {

    void sendEvent(String topic, SagaEvent sagaEvent);

}

