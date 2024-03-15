package microservice.orchestrator.application.port;

import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.config.kafka.ETopics;

public interface SagaExecutionControl {

    ETopics getNextTopic(SagaEvent event);

}

