package microservice.microtorneios.adapters.utils;

import microservice.microtorneios.application.core.domain.Event;

public interface JsonUtil {

    String toJson(Object object);

    Event toEvent(String json);
}

