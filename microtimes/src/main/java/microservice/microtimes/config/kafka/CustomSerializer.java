package microservice.microtimes.config.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservice.microtimes.adapter.out.message.TimeMessage;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class CustomSerializer implements Serializer<TimeMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, TimeMessage timeMessage) {

        try {
            if (timeMessage == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(timeMessage);
        } catch (Exception ex) {
            throw new SerializationException("Erro ao serializar TimeMessage para byte[].");
        }
    }
}

