package microservice.orchestrator.application.core.usecase;

import microservice.orchestrator.application.core.domain.History;
import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.core.domain.enums.EEventSource;
import microservice.orchestrator.application.core.domain.enums.ESagaStatus;
import microservice.orchestrator.application.port.SagaExecutionControl;
import microservice.orchestrator.application.port.input.SagaEventStartInputPort;
import microservice.orchestrator.application.port.output.CarteiroNotifyTopicOutputPort;
import microservice.orchestrator.config.kafka.ETopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;

public class SagaEventStartUseCase implements SagaEventStartInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventStartUseCase.class);

    private final SagaExecutionControl sagaExecutionControl;

    private final CarteiroNotifyTopicOutputPort carteiroNotifyTopicOutputPort;

    public SagaEventStartUseCase(
            SagaExecutionControl sagaExecutionControl,
            CarteiroNotifyTopicOutputPort carteiroNotifyTopicOutputPort) {
        this.sagaExecutionControl = sagaExecutionControl;
        this.carteiroNotifyTopicOutputPort = carteiroNotifyTopicOutputPort;
    }

    @Override
    public SagaEvent startSaga(SagaEvent event) {

        log.info("SAGA INICIADA");

        event.setSource(EEventSource.ORCHESTRATOR);
        event.setStatus(ESagaStatus.SUCCESS);
        this.addHistory(event, "Saga iniciada!");

        var topic = this.getTopic(event);
        this.sendToProducerWithTopic(event, topic);

        return event;
    }

    private ETopics getTopic(SagaEvent event) {
        return this.sagaExecutionControl.getNextTopic(event);
    }

    private void addHistory(SagaEvent event, String message) {
        var history = new History();
        history.setMessage(message);
        history.setSource(event.getSource());
        history.setStatus(event.getStatus());
        history.setCreatedAt(OffsetDateTime.now());

        event.addToHistory(history);
    }

    private void sendToProducerWithTopic(SagaEvent event, ETopics topics) {
        this.carteiroNotifyTopicOutputPort.sendEvent(topics.getTopic(), event);
    }

}

