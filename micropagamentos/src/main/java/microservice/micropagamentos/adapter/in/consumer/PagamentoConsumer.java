package microservice.micropagamentos.adapter.in.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micropagamentos.adapter.utils.JsonUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PagamentoConsumer {

    private final JsonUtil jsonUtil;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.pagamento-success}"
    )
    public void consumeSuccessEvent(String payload) {
        log.info("Receiving success event {} from pagamento-success topic.", payload);
        var event = jsonUtil.toSagaEventRequest(payload);
        log.info(event.toString());
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.pagamento-fail}"
    )
    public void consumeFailEvent(String payload) {
        log.info("Receiving rollback event {} from pagamento-fail topic.", payload);
        var event = jsonUtil.toSagaEventRequest(payload);
        log.info(event.toString());
    }
}

