package microservice.microtimes.application.core.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.config.utils.JsonUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TimeValidationConsumer {

    private final JsonUtil jsonUtil;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-validation-success}"
    )
    public void consumeSuccessEvent(String payload) {
        log.info("Receiving success event {} from time-validation-success topic.", payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-validation-fail}"
    )
    public void consumeFailEvent(String payload) {
        log.info("Receiving rollback event {} from time-validation-fail topic.", payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }
}

