package microservice.orchestrator.application.core.usecase;

import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.core.usecase.utils.SagaHandler;
import microservice.orchestrator.application.port.SagaExecutionControl;
import microservice.orchestrator.config.exception.ValidationException;
import microservice.orchestrator.config.kafka.ETopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

public class SagaExecutionControlImpl implements SagaExecutionControl {

    private static final Logger log = LoggerFactory.getLogger(SagaExecutionControlImpl.class);

    @Override
    public ETopics getNextTopic(SagaEvent event) {
        if (ObjectUtils.isEmpty(event.getSource()) || ObjectUtils.isEmpty(event.getStatus())) {
            throw new ValidationException("Source ou Status não informado.");
        }
        var topico = this.findTopicBySourceAndStatus(event);
        this.logCurrentSaga(event, topico);
        return topico;
    }

    public ETopics findTopicBySourceAndStatus(SagaEvent event) {
        return Arrays.stream(SagaHandler.SAGA_HANDLER)
            .filter(row -> this.isEventSourceAndStatusValid(event, row))
            .map(indice -> indice[SagaHandler.TOPIC_INDEX])
            .findFirst()
            .map(ETopics.class::cast)
            .orElseThrow(() -> new ValidationException("Tópico não encontrado!"));
    }

    private boolean isEventSourceAndStatusValid(SagaEvent event, Object[] row) {
        var source = row[SagaHandler.EVENT_SOURCE_INDEX];
        var status = row[SagaHandler.SAGA_STATUS_INDEX];
        return event.getSource().equals(source) && event.getStatus().equals(status);
    }

    // Serve para mostrar o que acontece
    private void logCurrentSaga(SagaEvent event, ETopics topic) {
        var sagaId = this.createSagaId(event);
        var source = event.getSource();
        switch (event.getStatus()) {
            case SUCCESS -> log.info("### SAGA ATUAL: {} | SUCESSO | PRÓXIMO TÓPICO {} | {}", source, topic, sagaId);
            case ROLLBACK_PENDING -> log.info("### SAGA ATUAL: {} | ENVIANDO PARA ROLLBACK NO PRÓPRIO SERVIÇO | PRÓXIMO TÓPICO {} | {}", source, topic, sagaId);
            case FAIL -> log.info("### SAGA ATUAL: {} | ENVIANDO PARA ROLLBACK NO SERVIÇO ANTERIOR | PRÓXIMO TÓPICO {} | {}", source, topic, sagaId);
        }
    }

    private String createSagaId(SagaEvent event) {
        return String.format("TRANSACTION ID %s | EVENT ID %d | TIME ID %d | TORNEIO ID %d",
            event.getTransactionId(), event.getSagaEventId(), event.getTimeId(), event.getTorneioId());
    }

}

