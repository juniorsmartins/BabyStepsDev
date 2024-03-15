package microservice.orchestrator.application.core.usecase;

import microservice.orchestrator.application.core.domain.History;
import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.core.domain.enums.EEventSource;
import microservice.orchestrator.application.core.domain.enums.ESagaStatus;
import microservice.orchestrator.application.port.input.SagaEventFinishSuccessInputPort;
import microservice.orchestrator.application.port.output.CarteiroNotifyTopicOutputPort;
import microservice.orchestrator.config.kafka.ETopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;

public class SagaEventFinishSuccessUseCase implements SagaEventFinishSuccessInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventFinishSuccessUseCase.class);

    private final CarteiroNotifyTopicOutputPort carteiroNotifyTopicOutputPort;

    public SagaEventFinishSuccessUseCase(
            CarteiroNotifyTopicOutputPort carteiroNotifyTopicOutputPort) {
        this.carteiroNotifyTopicOutputPort = carteiroNotifyTopicOutputPort;
    }

    @Override
    public SagaEvent finishSagaSuccess(SagaEvent event) {

        log.info("SAGA FINALIZADA COM SUCESSO PARA O EVENTO: {}", event.getSagaEventId());

        event.setSource(EEventSource.ORCHESTRATOR);
        event.setStatus(ESagaStatus.SUCCESS);

        this.addHistory(event, "Saga finalizada com sucesso!");
        this.notifyFinishedSaga(event);

        return event;
    }

    private void addHistory(SagaEvent event, String message) {
        var history = new History();
        history.setMessage(message);
        history.setSource(event.getSource().getValue());
        history.setStatus(event.getStatus());
        history.setCreatedAt(OffsetDateTime.now());

        event.addToHistory(history);
    }

    private void notifyFinishedSaga(SagaEvent event) {
        this.carteiroNotifyTopicOutputPort.sendEvent(ETopics.NOTIFY_ENDING.getTopic(), event);
    }

}

