package microservice.orchestrator.adapter.out.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.orchestrator.adapter.mapper.MapperOut;
import microservice.orchestrator.adapter.utils.JsonUtil;
import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.port.output.CarteiroNotifyTopicOutputPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class CarteiroNotifyTopicProducer implements CarteiroNotifyTopicOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final MapperOut mapperOut;

    private final JsonUtil jsonUtil;

    @Override
    public void sendEvent(String topic, SagaEvent sagaEvent) {

        Optional.ofNullable(sagaEvent)
            .map(this.mapperOut::toSagaEventRequest)
            .map(this.jsonUtil::toJson)
            .ifPresentOrElse(payload -> this.dispatcher(topic, payload),
                () -> {}
            );
    }

    private void dispatcher(String topic, String payload) {
        try {
            log.info("Sending event to topic {} with data {}", topic, payload);
            kafkaTemplate.send(topic, payload);

        } catch (Exception ex) {
            log.error("Error trying to send data to topic {} with data {}", topic, payload, ex);
        }
    }
}

