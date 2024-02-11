package microservice.microinscricoes.adapter.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.in.mapper.MapperIn;
import microservice.microinscricoes.adapter.utils.JsonUtilImpl;
import microservice.microinscricoes.application.port.input.TorneioSaveInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventConsumer {

    private final JsonUtilImpl jsonUtilImpl;

    private final MapperIn mapper;

    private final TorneioSaveInputPort torneioSaveInputPort;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.notify-ending}"
    )
    public void consumeNotifyEndingEvent(String payload) {
        log.info("Receiving ending notification event {} from notify-ending topic.", payload);
        var event = jsonUtilImpl.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.torneio-save}"
    )
    public void consumeTorneioSaveEvent(String payload) {

        log.info("Iniciada mensageria, via tópico torneio-save, para salvar Torneio.");

        Optional.ofNullable(payload)
            .map(this.jsonUtilImpl::toTorneioSaveDto)
            .map(this.mapper::toTorneio)
            .map(tournament -> {
                this.torneioSaveInputPort.save(tournament);
                return true;
            })
            .orElseThrow();

        log.info("Finalizada mensageria, via tópico torneio-save, para salvar Torneio {}.", payload);
    }
}

