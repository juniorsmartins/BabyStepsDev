package microservice.microinscricoes.adapter.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTime;
import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTorneio;
import microservice.microinscricoes.adapter.mapper.MapperIn;
import microservice.microinscricoes.adapter.utils.JsonUtil;
import microservice.microinscricoes.application.port.input.TimeCreateInputPort;
import microservice.microinscricoes.application.port.input.TorneioCreateInputPort;
import microservice.microinscricoes.application.port.output.SagaEventSaveOutputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerEvent {

    private final JsonUtil jsonUtil;

    private final MapperIn mapper;

    private final TorneioCreateInputPort torneioSaveInputPort;

    private final TimeCreateInputPort timeSaveInputPort;

    private final SagaEventSaveOutputPort sagaEventSaveOutputPort;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.notify-ending}"
    )
    public void consumeNotifyEndingEvent(String payload) {

        log.info("Recebido evento de conclusão da saga, via tópico notify-ending, no ConsumerEvent.");

        var sagaEvent = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapper::toSagaEvent)
            .map(this.sagaEventSaveOutputPort::save)
            .orElseThrow();

        log.info("Saga salva e finalizada com sucesso, com os dados: {}", sagaEvent);
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

