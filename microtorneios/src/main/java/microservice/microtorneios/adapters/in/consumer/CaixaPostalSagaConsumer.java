package microservice.microtorneios.adapters.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.mapper.MapperIn;
import microservice.microtorneios.adapters.utils.JsonUtil;
import microservice.microtorneios.application.port.input.SagaEventFailInputPort;
import microservice.microtorneios.application.port.input.SagaEventSuccessInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CaixaPostalSagaConsumer {

    private final SagaEventSuccessInputPort sagaEventSuccessInputPort;

    private final SagaEventFailInputPort sagaEventFailInputPort;

    private final JsonUtil jsonUtil;

    private final MapperIn mapperIn;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.torneio-success}"
    )
    public void consumeSuccessSagaEvent(String payload) {

        log.info("Recebido evento no tópico Torneio-Success para inscrever Time no Torneio.");

        var sagaEventSuccess = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventSuccessInputPort::addTimeInTorneio)
            .orElseThrow();

        log.info("Finalizado evento no tópico Torneio-Success para inscrever Time no Torneio: {}.", sagaEventSuccess);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.torneio-fail}"
    )
    public void consumeFailSagaEvent(String payload) {

        log.info("Recebido evento no tópico Time-Validation-Fail para remover Time do Torneio.");

        var sagaEventFail = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventFailInputPort::rollbackEvent)
            .orElseThrow();

        log.info("Finalizado evento no tópico Torneio-Fail para remover Time do Torneio: {}.", sagaEventFail);
    }
}

