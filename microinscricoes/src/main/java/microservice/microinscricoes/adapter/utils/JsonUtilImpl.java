package microservice.microinscricoes.adapter.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTime;
import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTorneio;
import microservice.microinscricoes.adapter.out.producer.SagaEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
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
    public SagaEvent toSagaEvent(String json) {
        try {
            return objectMapper.readValue(json, SagaEvent.class);

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
    public EventCreateTime toEventCreateTime(String json) {
        try {
            return objectMapper.readValue(json, EventCreateTime.class);

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return null;
        }
    }
}

