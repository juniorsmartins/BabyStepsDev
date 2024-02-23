package microservice.microtorneios.adapters.utils;

import microservice.microtorneios.application.core.domain.SagaEvent;

public interface JsonUtil {

    String toJson(Object object);

    SagaEvent toSagaEvent(String json);
}

