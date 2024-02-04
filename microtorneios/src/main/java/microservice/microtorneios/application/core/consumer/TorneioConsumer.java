package microservice.microtorneios.application.core.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.config.utils.JsonUtil;
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
}

