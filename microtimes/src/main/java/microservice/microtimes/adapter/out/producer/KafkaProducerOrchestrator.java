package microservice.microtimes.adapter.out.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.application.port.output.SagaEventSendOrchestratorOutputPot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerOrchestrator implements SagaEventSendOrchestratorOutputPot {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.orchestrator}")
    private String orchestratorTopic;

    @Override
    public void sendEvent(String payload) {
        try {
            log.info("Sending event to topic {} with data {}", orchestratorTopic, payload);
            kafkaTemplate.send(orchestratorTopic, payload);

        } catch (Exception ex) {
            log.error("Error trying to send data to topic {} with data {}", orchestratorTopic, payload, ex);
        }
    }
}

