package microservice.micropagamentos.adapter.utils;

import microservice.micropagamentos.adapter.in.consumer.SagaEvent;

public interface JsonUtil {

    String toJson(Object object);

    SagaEvent toSagaEvent(String json);
}

