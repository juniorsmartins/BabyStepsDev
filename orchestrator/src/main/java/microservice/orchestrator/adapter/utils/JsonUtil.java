package microservice.orchestrator.adapter.utils;

import microservice.orchestrator.application.core.domain.SagaEvent;

public interface JsonUtil {

    String toJson(Object object);

    SagaEvent toSagaEvent(String json);
}

