package microservice.microtorneios.adapters.out.utils;

import microservice.microtorneios.adapters.out.dto.EventFindIdTorneio;
import microservice.microtorneios.application.core.domain.Event;

public interface JsonUtil {

    String toJson(Object object);

    Event toEvent(String json);

    EventFindIdTorneio toEventFindIdTorneio(String json);
}

