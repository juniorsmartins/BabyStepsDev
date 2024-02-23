package microservice.microinscricoes.adapter.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTime;
import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTorneio;
import microservice.microinscricoes.adapter.in.mapper.MapperIn;
import microservice.microinscricoes.adapter.utils.JsonUtil;
import microservice.microinscricoes.application.port.input.TimeSaveInputPort;
import microservice.microinscricoes.application.port.input.TorneioSaveInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerEvent {

    private final JsonUtil jsonUtil;

    private final MapperIn mapper;

    private final TorneioSaveInputPort torneioSaveInputPort;

    private final TimeSaveInputPort timeSaveInputPort;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.notify-ending}"
    )
    public void consumeNotifyEndingEvent(String payload) {
        log.info("Receiving ending notification event {} from notify-ending topic.", payload);
        var event = jsonUtil.toSagaEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.torneio-save}"
    )
    public void consumeEventCreateTorneio(String payload) {

        log.info("Iniciada mensageria, via tópico torneio-save, para salvar Torneio.");

        var response = Optional.ofNullable(payload)
            .map(this.jsonUtil::toEventCreateTorneio)
            .map(EventCreateTorneio::torneio)
            .map(this.mapper::toTorneio)
            .map(this.torneioSaveInputPort::save)
            .orElseThrow();

        log.info("Finalizada mensageria, via tópico torneio-save, para salvar Torneio {}.", response);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-save}"
    )
    public void consumeEventCreateTime(String payload) {

        log.info("Iniciada mensageria, via tópico time-save, para salvar Time.");

        var response = Optional.ofNullable(payload)
            .map(this.jsonUtil::toEventCreateTime)
            .map(EventCreateTime::time)
            .map(this.mapper::toTime)
            .map(this.timeSaveInputPort::save)
            .orElseThrow();

        log.info("Finalizada mensageria, via tópico time-save, para salvar Time {}.", response);
    }
}

