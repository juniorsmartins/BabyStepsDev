package microservice.microinscricoes.adapter.out.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.application.port.output.KafkaProducerFindIdTorneioOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerFindIdTorneio implements KafkaProducerFindIdTorneioOutputPort {

    private final KafkaTemplate<String, Long> kafkaTemplateFindIdTorneio;

    @Value("${spring.kafka.topic.find-id-torneio}")
    private String findIdTorneioTopic;

    @Override
    public void sendFindIdTorneioEvent(Long id) {
        try {
            log.info("Enviado evento para o tópico {}, com id {}", findIdTorneioTopic, id);
            kafkaTemplateFindIdTorneio.send(findIdTorneioTopic, id);

        } catch (Exception ex) {
            log.error("Erro ao enviar evento para o tópico {}", findIdTorneioTopic, ex);
        }
    }
}

