package microservice.microinscricoes.adapter.out.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.out.event.EventFindIdTorneio;
import microservice.microinscricoes.adapter.out.utils.JsonUtil;
import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.port.output.KafkaProducerFindIdTorneioOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerFindIdTorneio implements KafkaProducerFindIdTorneioOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final JsonUtil jsonUtil;

    @Value("${spring.kafka.topic.find-id-torneio}")
    private String findIdTorneioTopic;

    @Override
    public void sendFindIdTorneioEvent(Inscricao inscricao) {
        var event = new EventFindIdTorneio(inscricao.getId(), inscricao.getTorneioId());

        try {
            log.info("Enviado evento para o tópico {}, com inscricaoId {} e torneioId {}", findIdTorneioTopic,
                event.inscricaoId(), event.torneioId());
            var payload = this.jsonUtil.toJson(event);
            kafkaTemplate.send(findIdTorneioTopic, payload);

        } catch (Exception ex) {
            log.error("Erro ao enviar evento para o tópico {}", findIdTorneioTopic, ex);
        }
    }
}

