package microservice.microtimes.adapter.in.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.mapper.MapperIn;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.port.input.SagaEventFailInputPort;
import microservice.microtimes.application.port.input.SagaEventSuccessInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class CaixaPostalSagaConsumer {

    private final SagaEventSuccessInputPort sagaEventSuccessInputPort;

    private final SagaEventFailInputPort sagaEventFailInputPort;

    private final JsonUtil jsonUtil;

    private final MapperIn mapperIn;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-validation-success}"
    )
    public void consumeSuccessSagaEvent(String payload) {

        log.info("Recebido evento no tópico Time-Validation-Success para inscrever Torneio no Time.");

        var sagaEventSuccess = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventSuccessInputPort::addTorneioInTime)
            .map(this.mapperIn::toSagaEventRequest)
            .orElseThrow();

        log.info("Finalizado evento no tópico Time-Validation-Success para inscrever Torneio no Time: {}.", sagaEventSuccess);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-validation-fail}"
    )
    public void consumeFailSagaEvent(String payload) {

        log.info("Recebido evento no tópico Time-Validation-Fail para remover Torneio do Time.");

        var sagaEventFail = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventFailInputPort::rollbackEvent)
            .map(this.mapperIn::toSagaEventRequest)
            .orElseThrow();

        log.info("Finalizado evento no tópico Torneio-Fail para remover Torneio do Time: {}.", sagaEventFail);
    }
}

