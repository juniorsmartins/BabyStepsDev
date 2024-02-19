package microservice.microinscricoes.adapter.utils;

import microservice.microinscricoes.adapter.in.consumer.EventCreateTime;
import microservice.microinscricoes.adapter.in.dto.kafka.EventCreateTorneio;
import microservice.microinscricoes.application.core.domain.Event;

public interface JsonUtil {

    String toJson(Object object);

    Event toEvent(String json);

    EventCreateTorneio toEventCreateTorneio(String json);

    EventCreateTime toEventCreateTime(String json);
}

