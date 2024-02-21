package microservice.orchestrator.adapter.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.orchestrator.adapter.in.consumer.SagaEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return "";
        }
    }

    public SagaEvent toSagaEvent(String json) {
        try {
            return objectMapper.readValue(json, SagaEvent.class);

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return null;
        }
    }
}

