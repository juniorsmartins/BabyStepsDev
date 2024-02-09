package microservice.microtorneios.adapters.in.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.in.mapper.EventMapperIn;
import microservice.microtorneios.adapters.in.utils.JsonUtil;
import microservice.microtorneios.application.port.input.FindIdTorneioInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class TorneioConsumer {

    private final JsonUtil jsonUtil;

    private EventMapperIn eventMapperIn;

    private FindIdTorneioInputPort findIdTorneioInputPort;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.torneio-success}"
    )
    public void consumeSuccessEvent(String payload) {
        log.info("Receiving success event {} from torneio-success topic.", payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.torneio-fail}"
    )
    public void consumeFailEvent(String payload) {
        log.info("Receiving rollback event {} from torneio-fail topic.", payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.find-id-torneio}"
    )
    public void consumeFindIdTorneioEvent(String payload) {
        log.info("Bem-sucedida recebimento de evento para o tópico find-id-torneio, com id {}.", payload);

        Optional.ofNullable(payload)
            .map(this.jsonUtil::toEventFindIdTorneio)
            .map(this.eventMapperIn::toEventFindIdTorneio)
            .map(this.findIdTorneioInputPort::findId)
            .orElseThrow();

        log.info(payload);
    }
}

