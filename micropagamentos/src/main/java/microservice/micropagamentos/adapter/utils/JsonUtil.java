package microservice.micropagamentos.adapter.utils;

import microservice.micropagamentos.adapter.in.consumer.event.SagaEventRequest;

public interface JsonUtil {

    String toJson(Object object);

    SagaEventRequest toSagaEventRequest(String json);
}

