package microservice.microtimes.adapter.in.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.mapper.MapperIn;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.port.input.SagaEventSuccessValidationInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class TimeValidationConsumer {

    private final JsonUtil jsonUtil;

    private final MapperIn mapperIn;

    private final SagaEventSuccessValidationInputPort sagaEventSuccessValidationInputPort;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-validation-success}"
    )
    public void consumeSuccessSagaEvent(String payload) {

        log.info("Recebendo evento de sucesso no tópico de sucesso de validação de time.");

        var response = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventSuccessValidationInputPort::createSuccessValidation)
            .orElseThrow();

        log.info("Finalizado evento de sucesso no tópico de sucesso de validação de time: {}.", response);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-validation-fail}"
    )
    public void consumeFailSagaEvent(String payload) {

        log.info("Recebendo evento de reversão no tópico de falha na validação de time.");

        var event = jsonUtil.toSagaEventRequest(payload);

        log.info("Finalizado evento de falha no tópico de falha de validação de time: {}.", payload);

        log.info(event.toString());
    }
}

