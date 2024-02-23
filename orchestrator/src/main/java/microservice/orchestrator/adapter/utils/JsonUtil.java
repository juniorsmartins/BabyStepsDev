package microservice.orchestrator.adapter.utils;

import microservice.orchestrator.adapter.in.consumer.SagaEvent;

public interface JsonUtil {

    String toJson(Object object);

    SagaEvent toSagaEvent(String json);
}

