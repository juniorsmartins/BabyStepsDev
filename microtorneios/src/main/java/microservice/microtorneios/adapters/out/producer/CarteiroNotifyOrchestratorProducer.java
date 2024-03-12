package microservice.microtorneios.adapters.out.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.application.port.output.SagaEventOrchestratorOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarteiroNotifyOrchestratorProducer implements SagaEventOrchestratorOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.orchestrator}")
    private String orchestratorTopic;

    @Override
    public void sendEvent(String payload) {
        try {
            log.info("Enviando evento para tópico {} com payload {}", orchestratorTopic, payload);
            kafkaTemplate.send(orchestratorTopic, payload);

        } catch (Exception ex) {
            log.error("Erro ao tentar enviar dados para o tópico {} com payload {}", orchestratorTopic, payload, ex);
        }
    }
}

