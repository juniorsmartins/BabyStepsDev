package microservice.microinscricoes.adapter.utils;

import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTime;
import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTorneio;
import microservice.microinscricoes.adapter.out.producer.SagaEvent;

public interface JsonUtil {

    String toJson(Object object);

    SagaEvent toSagaEvent(String json);

    EventCreateTorneio toEventCreateTorneio(String json);

    EventCreateTime toEventCreateTime(String json);
}

