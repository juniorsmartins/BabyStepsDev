package microservice.microtorneios.config.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservice.microtorneios.adapters.out.message.TimeMessage;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class CustomDeserializer implements Deserializer<TimeMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public TimeMessage deserialize(String topic, byte[] data) {

        try {
            if (data == null) {
                return null;
            }
            return this.objectMapper.readValue(new String(data, StandardCharsets.UTF_8), TimeMessage.class);
        } catch (Exception ex) {
            throw new SerializationException("Erro ao deserializar byte[] para TimeMessage.");
        }
    }
}

