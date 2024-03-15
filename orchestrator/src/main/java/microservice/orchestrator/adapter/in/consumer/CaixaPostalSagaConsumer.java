package microservice.orchestrator.adapter.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.orchestrator.adapter.mapper.MapperIn;
import microservice.orchestrator.adapter.utils.JsonUtil;
import microservice.orchestrator.application.port.input.SagaEventContinueInputPort;
import microservice.orchestrator.application.port.input.SagaEventFinishFailInputPort;
import microservice.orchestrator.application.port.input.SagaEventFinishSuccessInputPort;
import microservice.orchestrator.application.port.input.SagaEventStartInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CaixaPostalSagaConsumer {

    private final SagaEventStartInputPort sagaEventStartInputPort;

    private final SagaEventContinueInputPort sagaEventContinueInputPort;

    private final SagaEventFinishSuccessInputPort sagaEventFinishSuccessInputPort;

    private final SagaEventFinishFailInputPort sagaEventFinishFailInputPort;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.start-saga}"
    )
    public void consumeStartSagaEvent(String payload) {

        log.info("Recebido evento no tópico Start-Saga para inscrever Time no Torneio.");

        var response = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventStartInputPort::startSaga)
            .orElseThrow();

        log.info("Finalizado evento no tópico Start-Saga para inscrever Time no Torneio: {}.", response);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.orchestrator}"
    )
    public void consumeOrchestratorSagaEvent(String payload) {

        log.info("Recebido evento no tópico Orchestrator para inscrever Time no Torneio.");

        var response = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventContinueInputPort::continueSaga)
            .orElseThrow();

        log.info("Finalizado evento no tópico Orchestrator para inscrever Time no Torneio: {}.", response);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.finish-success}"
    )
    public void consumeFinishSuccessSagaEvent(String payload) {

        log.info("Recebido evento no tópico Finish-Success para inscrever Time no Torneio.");

        var response = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventFinishSuccessInputPort::finishSagaSuccess)
            .orElseThrow();

        log.info("Finalizado evento no tópico Finish-Success para inscrever Time no Torneio: {}.", response);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.finish-fail}"
    )
    public void consumeFinishFailSagaEvent(String payload) {

        log.info("Recebido evento no tópico Finish-Fail para inscrever Time no Torneio.");

        var response = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventFinishFailInputPort::finishFail)
            .orElseThrow();

        log.info("Finalizado evento no tópico Finish-Fail para inscrever Time no Torneio: {}.", response);
    }

}

