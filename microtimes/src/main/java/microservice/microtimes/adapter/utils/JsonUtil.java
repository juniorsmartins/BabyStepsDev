package microservice.microtimes.adapter.utils;

import microservice.microtimes.adapter.in.consumer.SagaEvent;

public interface JsonUtil {

    String toJson(Object object);

    SagaEvent toSagaEvent(String json);
}

