package microservice.orchestrator.saga;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.orchestrator.adapter.out.producer.SagaOrchestratorProducer;
import microservice.orchestrator.adapter.utils.JsonUtil;
import microservice.orchestrator.application.core.domain.History;
import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.core.domain.enums.EEventSource;
import microservice.orchestrator.application.core.domain.enums.ESagaStatus;
import microservice.orchestrator.config.kafka.ETopics;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class OrchestratorService {

    private final SagaOrchestratorProducer sagaOrchestratorProducer;

    private final SagaExecutionControl sagaExecutionControl;

    private final JsonUtil jsonUtil;

    public void startSaga(SagaEvent event) {
        event.setSource(EEventSource.ORCHESTRATOR);
        event.setStatus(ESagaStatus.SUCCESS);
        var topico = this.getTopic(event);
        log.info("SAGA INICIADA");
        this.addHistory(event, "Saga iniciada!");
        this.sendToProducerWithTopic(event, topico);
    }

    public void continueSaga(SagaEvent event) {
        var topico = this.getTopic(event);
        log.info("SAGA CONTINUA PARA O EVENTO: {}", event.getSagaEventId());
        this.sendToProducerWithTopic(event, topico);
    }

    public void finishSagaSuccess(SagaEvent event) {
        event.setSource(EEventSource.ORCHESTRATOR);
        event.setStatus(ESagaStatus.SUCCESS);
        log.info("SAGA FINALIZADA COM SUCESSO PARA O EVENTO: {}", event.getSagaEventId());
        this.addHistory(event, "Saga finalizada com sucesso!");
        this.notifyFinishedSaga(event);
    }

    public void finishSagaFail(SagaEvent event) {
        event.setSource(EEventSource.ORCHESTRATOR);
        event.setStatus(ESagaStatus.FAIL);
        log.info("SAGA FINALIZADA COM ERRO PARA O EVENTO: {}", event.getSagaEventId());
        this.addHistory(event, "Saga finalizada com erro!");
        this.notifyFinishedSaga(event);
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

    private void notifyFinishedSaga(SagaEvent event) {
        this.sagaOrchestratorProducer.sendEvent(ETopics.NOTIFY_ENDING.getTopic(), this.jsonUtil.toJson(event));
    }

    private void sendToProducerWithTopic(SagaEvent event, ETopics topics) {
        var payload = this.jsonUtil.toJson(event);
        this.sagaOrchestratorProducer.sendEvent(topics.getTopic(), payload);
    }

}

