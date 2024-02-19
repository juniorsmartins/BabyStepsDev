package microservice.microtorneios.adapters.out.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.utils.EncapsulateEvent;
import microservice.microtorneios.adapters.utils.JsonUtil;
import microservice.microtorneios.application.core.domain.Torneio;
import microservice.microtorneios.application.port.output.NotifyCreationOfNewTorneioOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotifyCreationOfNewTorneioKafkaProducer implements NotifyCreationOfNewTorneioOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final EncapsulateEvent encapsulateEvent;

    private final JsonUtil jsonUtil;

    @Value("${spring.kafka.topic.torneio-save}")
    private String torneioSaveTopic;

    @Override
    public void sendEvent(Torneio torneio) {

        Optional.ofNullable(torneio)
            .map(this.encapsulateEvent::toEventCreateTorneio)
            .map(this.jsonUtil::toJson)
            .map(payload -> {
                try {
                    log.info("Enviando evento para o tópico {}, com os dados {}", torneioSaveTopic, payload);
                    kafkaTemplate.send(torneioSaveTopic, payload);

                } catch (Exception ex) {
                    log.error("Erro ao tentar enviar dados para o tópico {}, com o payload {}", torneioSaveTopic, payload, ex);
                }
                return payload;
            })
            .orElseThrow();
    }
}

