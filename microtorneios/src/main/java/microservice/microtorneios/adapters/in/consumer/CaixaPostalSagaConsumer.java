package microservice.microtorneios.adapters.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.mapper.MapperIn;
import microservice.microtorneios.adapters.utils.JsonUtil;
import microservice.microtorneios.application.port.input.SagaEventValidationInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CaixaPostalSagaConsumer {

    private final SagaEventValidationInputPort sagaEventValidationInputPort;

    private final JsonUtil jsonUtil;

    private final MapperIn mapperIn;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.torneio-success}"
    )
    public void consumeSuccessSagaEvent(String payload) {

        log.info("Recebendo evento no tópico de sucesso de validação de Torneio.");

        var sagaEventSuccess = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventValidationInputPort::createValidation)
            .orElseThrow();

        log.info("Finalizado evento no tópico de sucesso de validação de Torneio: {}.", sagaEventSuccess);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.torneio-fail}"
    )
    public void consumeFailEvent(String payload) {
        log.info("Receiving rollback event {} from torneio-fail topic.", payload);
        var event = jsonUtil.toSagaEventRequest(payload);
        log.info(event.toString());
    }
}

