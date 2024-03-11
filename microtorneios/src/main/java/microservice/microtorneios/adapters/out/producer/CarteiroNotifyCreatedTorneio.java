package microservice.microtorneios.adapters.out.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.out.producer.dto.TorneioIdDto;
import microservice.microtorneios.adapters.out.producer.event.EventCreateTorneio;
import microservice.microtorneios.adapters.utils.JsonUtil;
import microservice.microtorneios.application.core.domain.Torneio;
import microservice.microtorneios.application.port.output.CarteiroNotifyCreatedTorneioOutputPort;
import microservice.microtorneios.config.exception.http_500.CarteiroFailSendLetterException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarteiroNotifyCreatedTorneio implements CarteiroNotifyCreatedTorneioOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final JsonUtil jsonUtil;

    @Value("${spring.kafka.topic.torneio-save}")
    private String torneioSaveTopic;

    @Override
    public void sendEvent(Torneio torneio) {

        Optional.ofNullable(torneio)
            .map(this::toEventCreateTorneio)
            .map(this.jsonUtil::toJson)
            .ifPresentOrElse(this::dispatch, () -> {throw new CarteiroFailSendLetterException();});
    }

    public EventCreateTorneio toEventCreateTorneio(Torneio torneio) {

        return Optional.ofNullable(torneio)
            .map(tournament -> new TorneioIdDto(tournament.getId()))
            .map(EventCreateTorneio::new)
            .orElseThrow();
    }

    private void dispatch(String payload) {
        try {
            log.info("Carteiro despacha para o tópico {}, com o conteúdo {}", torneioSaveTopic, payload);
            kafkaTemplate.send(torneioSaveTopic, payload);

        } catch (Exception ex) {
            log.error("Carteiro falha na tentativa de despachar para o tópico {}, com o conteúdo {}", torneioSaveTopic, payload, ex);
        }
    }

}

