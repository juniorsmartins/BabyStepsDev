package microservice.microtimes.adapter.utils;

import microservice.microtimes.application.core.domain.SagaEvent;
import microservice.microtimes.adapter.in.consumer.event.SagaEventRequest;

public interface JsonUtil {

    String toJson(Object object);

    SagaEvent toSagaEvent(String json);

    SagaEventRequest toSagaEventRequest(String json);
}

