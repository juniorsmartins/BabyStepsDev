package microservice.orchestrator.adapter.utils;

import microservice.orchestrator.adapter.in.consumer.event.SagaEventRequest;

public interface JsonUtil {

    String toJson(Object object);

    SagaEventRequest toSagaEventRequest(String json);
}

