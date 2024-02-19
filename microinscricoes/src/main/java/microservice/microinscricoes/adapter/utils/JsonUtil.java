package microservice.microinscricoes.adapter.utils;

import microservice.microinscricoes.adapter.in.dto.kafka.EventCreateTorneio;
import microservice.microinscricoes.adapter.in.dto.request.TimeSaveDto;
import microservice.microinscricoes.adapter.in.dto.request.TorneioSaveDto;
import microservice.microinscricoes.application.core.domain.Event;

public interface JsonUtil {

    String toJson(Object object);

    Event toEvent(String json);

    TorneioSaveDto toTorneioSaveDto(String json);

    TimeSaveDto toTimeSaveDto(String json);

    EventCreateTorneio toEventCreateTorneio(String json);
}

