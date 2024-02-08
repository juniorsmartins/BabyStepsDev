package microservice.microtorneios.adapters.out.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.out.dto.EventFindIdTorneio;
import microservice.microtorneios.application.core.domain.Event;
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
    public EventFindIdTorneio toEventFindIdTorneio(String json) {
        try {
            return objectMapper.readValue(json, EventFindIdTorneio.class);

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return null;
        }
    }
}
