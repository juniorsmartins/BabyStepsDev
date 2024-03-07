package microservice.microtimes.adapter.in.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.mapper.MapperIn;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.port.input.SagaEventValidationInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class TimeValidationConsumer {

    private final JsonUtil jsonUtil;

    private final MapperIn mapperIn;

    private final SagaEventValidationInputPort sagaEventValidationInputPort;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-validation-success}"
    )
    public void consumeSuccessSagaEvent(String payload) {

        log.info("Recebendo evento no tópico de sucesso de validação de time.");

        var sagaEventSuccess = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventValidationInputPort::createValidation)
            .orElseThrow();

        log.info("Finalizado evento no tópico de sucesso de validação de time: {}.", sagaEventSuccess);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-validation-fail}"
    )
    public void consumeFailSagaEvent(String payload) {

        log.info("Recebendo evento no tópico de falha na validação de time.");

        var sagaEventFail = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventValidationInputPort::rollbackEvent)
            .orElseThrow();

        log.info("Finalizado evento no tópico de falha de validação de time: {}.", sagaEventFail);
    }
}

