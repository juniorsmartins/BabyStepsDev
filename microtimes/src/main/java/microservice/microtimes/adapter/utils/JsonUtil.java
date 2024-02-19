package microservice.microtimes.adapter.utils;

import microservice.microtimes.adapter.in.dto.Event;

public interface JsonUtil {

    String toJson(Object object);

    Event toEvent(String json);
}

