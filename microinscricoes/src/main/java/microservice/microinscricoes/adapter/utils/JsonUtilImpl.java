package microservice.microinscricoes.adapter.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.in.dto.kafka.EventCreateTorneio;
import microservice.microinscricoes.adapter.in.dto.request.TimeSaveDto;
import microservice.microinscricoes.adapter.in.dto.request.TorneioSaveDto;
import microservice.microinscricoes.application.core.domain.Event;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class JsonUtilImpl implements JsonUtil {

    private final ObjectMapper objectMapper;

    @Override
    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return "";
        }
    }

    @Override
    public Event toEvent(String json) {
        try {
            return objectMapper.readValue(json, Event.class);

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public EventCreateTorneio toEventCreateTorneio(String json) {
        try {
            return objectMapper.readValue(json, EventCreateTorneio.class);

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public TorneioSaveDto toTorneioSaveDto(String json) {
        try {
            return objectMapper.readValue(json, TorneioSaveDto.class);

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public TimeSaveDto toTimeSaveDto(String json) {
        try {
            return objectMapper.readValue(json, TimeSaveDto.class);

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return null;
        }
    }
}

