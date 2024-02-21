package microservice.microinscricoes.adapter.utils;

import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTime;
import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTorneio;
import microservice.microinscricoes.adapter.out.producer.EventCreateInscrito;

public interface JsonUtil {

    String toJson(Object object);

    EventCreateInscrito toEvent(String json);

    EventCreateTorneio toEventCreateTorneio(String json);

    EventCreateTime toEventCreateTime(String json);
}

