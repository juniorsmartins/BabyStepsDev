package microservice.microtorneios.application.core.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.out.utils.JsonUtil;
import microservice.microtorneios.adapters.out.utils.JsonUtilImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TorneioConsumer {

    private final JsonUtil jsonUtil;

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
        var eventFindIdTorneio = jsonUtil.toEventFindIdTorneio(payload);
        log.info(eventFindIdTorneio.toString());
    }
}

