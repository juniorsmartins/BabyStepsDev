package microservice.microtimes.adapter.utils;

import microservice.microtimes.adapter.in.consumer.event.SagaEventRequest;

public interface JsonUtil {

    String toJson(Object object);

    SagaEventRequest toSagaEventRequest(String json);
}

