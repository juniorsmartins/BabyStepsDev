package microservice.microinscricoes.adapter.out.utils;

import microservice.microinscricoes.application.core.domain.Event;

public interface JsonUtil {

    String toJson(Object object);

    Event toEvent(String json);
}

