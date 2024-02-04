package microservice.orchestrator.application.core.producer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SagaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(String topic, String payload) {
        try {
            log.info("Sending event to topic {} with data {}", topic, payload);
            kafkaTemplate.send(topic, payload);

        } catch (Exception ex) {
            log.error("Error trying to send data to topic {} with data {}", topic, payload, ex);
        }
    }
}

