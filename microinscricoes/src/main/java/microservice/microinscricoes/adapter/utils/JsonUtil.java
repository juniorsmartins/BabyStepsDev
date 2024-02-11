package microservice.microinscricoes.adapter.utils;

import microservice.microinscricoes.application.core.domain.Event;

public interface JsonUtil {

    String toJson(Object object);

    Event toEvent(String json);
}

