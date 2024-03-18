package microservice.orchestrator.application.core.usecase;

import microservice.orchestrator.application.core.domain.History;
import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.core.domain.enums.EEventSource;
import microservice.orchestrator.application.core.domain.enums.ESagaStatus;
import microservice.orchestrator.application.port.input.SagaEventFinishFailInputPort;
import microservice.orchestrator.application.port.output.CarteiroNotifyTopicOutputPort;
import microservice.orchestrator.config.kafka.ETopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;

public class SagaEventFinishFailUseCase implements SagaEventFinishFailInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventFinishFailUseCase.class);

    private final CarteiroNotifyTopicOutputPort carteiroNotifyTopicOutputPort;

    public SagaEventFinishFailUseCase(
            CarteiroNotifyTopicOutputPort carteiroNotifyTopicOutputPort) {
        this.carteiroNotifyTopicOutputPort = carteiroNotifyTopicOutputPort;
    }

    @Override
    public SagaEvent finishFail(SagaEvent event) {

        log.info("SAGA FINALIZADA COM ERRO PARA O EVENTO: {}", event.getSagaEventId());

        event.setSource(EEventSource.ORCHESTRATOR);
        event.setStatus(ESagaStatus.FAIL);

        this.addHistory(event, "Saga finalizada com erro!");
        this.notifyFinishedSaga(event);

        return event;
    }

    private void addHistory(SagaEvent event, String message) {
        var history = new History();
        history.setMessage(message);
        history.setSource(event.getSource());
        history.setStatus(event.getStatus());
        history.setCreatedAt(OffsetDateTime.now());

        event.addToHistory(history);
    }

    private void notifyFinishedSaga(SagaEvent event) {
        this.carteiroNotifyTopicOutputPort.sendEvent(ETopics.NOTIFY_ENDING.getTopic(), event);
    }

}

