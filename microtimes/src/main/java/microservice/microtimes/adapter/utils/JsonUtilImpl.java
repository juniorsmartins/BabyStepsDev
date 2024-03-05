package microservice.microtimes.adapter.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.in.consumer.SagaEvent;
import microservice.microtimes.adapter.in.consumer.dto.SagaEventRequest;
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
    public SagaEventRequest toSagaEventRequest(String json) {
        try {
            return objectMapper.readValue(json, SagaEventRequest.class);

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return null;
        }
    }
}

