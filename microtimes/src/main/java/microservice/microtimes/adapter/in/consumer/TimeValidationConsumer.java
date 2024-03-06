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

    private final SagaEventValidationInputPort sagaEventSuccessValidationInputPort;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-validation-success}"
    )
    public void consumeSuccessSagaEvent(String payload) {

        log.info("Recebendo evento de sucesso no tópico de sucesso de validação de time.");

        Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(event -> {
                this.sagaEventSuccessValidationInputPort.createValidation(event);
                return true;
            })
            .orElseThrow();

        log.info("Finalizado evento de sucesso no tópico de sucesso de validação de time: {}.", payload);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-validation-fail}"
    )
    public void consumeFailSagaEvent(String payload) {

        log.info("Recebendo evento de reversão no tópico de falha na validação de time.");

        Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(event -> {
                this.sagaEventSuccessValidationInputPort.rollbackEvent(event);
                return true;
            })
            .orElseThrow();

        log.info("Finalizado evento de falha no tópico de falha de validação de time: {}.", payload);
    }
}

