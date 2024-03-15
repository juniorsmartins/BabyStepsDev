package microservice.orchestrator.application.core.usecase;

import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.port.SagaExecutionControl;
import microservice.orchestrator.application.port.input.SagaEventContinueInputPort;
import microservice.orchestrator.application.port.output.CarteiroNotifyTopicOutputPort;
import microservice.orchestrator.config.kafka.ETopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SagaEventContinueUseCase implements SagaEventContinueInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventContinueUseCase.class);

    private final SagaExecutionControl sagaExecutionControl;

    private final CarteiroNotifyTopicOutputPort carteiroNotifyTopicOutputPort;

    public SagaEventContinueUseCase(
            SagaExecutionControl sagaExecutionControl,
            CarteiroNotifyTopicOutputPort carteiroNotifyTopicOutputPort) {
        this.sagaExecutionControl = sagaExecutionControl;
        this.carteiroNotifyTopicOutputPort = carteiroNotifyTopicOutputPort;
    }

    @Override
    public SagaEvent continueSaga(SagaEvent event) {

        log.info("SAGA CONTINUA PARA O EVENTO: {}", event.getSagaEventId());

        var topico = this.getTopic(event);
        this.sendToProducerWithTopic(event, topico);

        return event;
    }

    private ETopics getTopic(SagaEvent event) {
        return this.sagaExecutionControl.getNextTopic(event);
    }

    private void sendToProducerWithTopic(SagaEvent event, ETopics topics) {
        this.carteiroNotifyTopicOutputPort.sendEvent(topics.getTopic(), event);
    }

}

