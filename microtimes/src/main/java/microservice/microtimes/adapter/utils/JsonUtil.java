package microservice.microtimes.adapter.utils;

import microservice.microtimes.adapter.in.consumer.SagaEvent;
import microservice.microtimes.adapter.in.consumer.dto.SagaEventRequest;

public interface JsonUtil {

    String toJson(Object object);

    SagaEvent toSagaEvent(String json);

    SagaEventRequest toSagaEventRequest(String json);
}

