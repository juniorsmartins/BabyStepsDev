package microservice.microtorneios.adapters.utils;

import microservice.microtorneios.adapters.in.consumer.event.SagaEventRequest;

public interface JsonUtil {

    String toJson(Object object);

    SagaEventRequest toSagaEventRequest(String json);
}

