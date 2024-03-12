package microservice.microtimes.application.core.usecase;

import microservice.microtimes.adapter.mapper.MapperIn;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.core.domain.History;
import microservice.microtimes.application.core.domain.SagaEvent;
import microservice.microtimes.application.core.domain.ValidationModel;
import microservice.microtimes.application.core.domain.enums.ESagaStatus;
import microservice.microtimes.application.port.input.SagaEventInputPort;
import microservice.microtimes.application.port.output.SagaEventExistsOutputPort;
import microservice.microtimes.application.port.output.SagaEventFindOutputPort;
import microservice.microtimes.application.port.output.SagaEventSaveOutputPort;
import microservice.microtimes.application.port.output.SagaEventOrchestratorOutputPort;
import microservice.microtimes.config.exception.http.SagaEventNullValueException;
import microservice.microtimes.config.exception.http.SagaEventDuplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.Optional;

public class SagaEventUseCase implements SagaEventInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventUseCase.class);

    private static final String CURRENT_SOURCE = "TIME-VALIDATION-SUCCESS";

    private final SagaEventExistsOutputPort sagaEventExistsOutputPort;

    private final SagaEventSaveOutputPort sagaEventSaveOutputPort;

    private final SagaEventOrchestratorOutputPort sagaEventOrchestratorOutputPort;

    private final SagaEventFindOutputPort sagaEventFindOutputPort;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventUseCase(SagaEventExistsOutputPort sagaEventExistsOutputPort,
                            SagaEventSaveOutputPort sagaEventSaveOutputPort,
                            SagaEventOrchestratorOutputPort sagaEventOrchestratorOutputPort,
                            SagaEventFindOutputPort sagaEventFindOutputPort,
                            MapperIn mapperIn,
                            JsonUtil jsonUtil) {
        this.sagaEventExistsOutputPort = sagaEventExistsOutputPort;
        this.sagaEventSaveOutputPort = sagaEventSaveOutputPort;
        this.sagaEventOrchestratorOutputPort = sagaEventOrchestratorOutputPort;
        this.sagaEventFindOutputPort = sagaEventFindOutputPort;
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public SagaEvent createValidation(SagaEvent sagaEvent) {

        log.info("Iniciado serviço para criar Success-Validation.");

        var validationCreated = Optional.ofNullable(sagaEvent)
            .map(event -> {
                try {
                    this.checkExistenceMandatoryValues(event);
                    this.checkExistenceValidationDuplication(event);
                    var model = this.createValidationModel(event, true);
                    this.sagaEventSaveOutputPort.save(model);
                    handleSuccess(event);

                } catch (SagaEventNullValueException | SagaEventDuplicationException ex) {
                    log.error("Erro: {}", ex.getMessage(), ex);
                    handleFailCurrentNotExecuted(sagaEvent, ex.getMessage());
                }
                this.sagaEventOrchestratorOutputPort.sendEvent(this.jsonUtil.toJson(event));
                return event;
            })
            .orElseThrow();

        log.info("Finalizado serviço para criar Success-Validation: {}.", validationCreated);

        return sagaEvent;
    }

    private void checkExistenceMandatoryValues(SagaEvent event) {
        if (ObjectUtils.isEmpty(event.getSagaEventId()) || ObjectUtils.isEmpty(event.getTransactionId())) {
            throw new SagaEventNullValueException();
        }
    }

    private void checkExistenceValidationDuplication(SagaEvent event) {
        var exists = this.sagaEventExistsOutputPort.existsDuplication(event.getSagaEventId(), event.getTransactionId());
        if (exists) {
            throw new SagaEventDuplicationException();
        }
    }

    private ValidationModel createValidationModel(SagaEvent sagaEvent, boolean success) {
        var validation = new ValidationModel();
        validation.setSagaEventId(sagaEvent.getSagaEventId());
        validation.setTransactionId(sagaEvent.getTransactionId());
        validation.setSuccess(success);

        return validation;
    }

    private void handleSuccess(SagaEvent event) {
        event.setStatus(ESagaStatus.SUCCESS);
        event.setSource(CURRENT_SOURCE);
        this.addHistory(event, "Validação bem-sucedida!");
    }

    private void addHistory(SagaEvent event, String message) {
        var history = new History();
        history.setMessage(message);
        history.setSource(event.getSource());
        history.setStatus(event.getStatus());
        history.setCreatedAt(OffsetDateTime.now());

        event.addToHistory(history);
    }

    private void handleFailCurrentNotExecuted(SagaEvent event, String message) {
        event.setStatus(ESagaStatus.ROLLBACK_PENDING);
        event.setSource(CURRENT_SOURCE);
        this.addHistory(event, "Falha na validação: ".concat(message));
    }

    @Override
    public SagaEvent rollbackEvent(SagaEvent event) {
        this.changeValidationToFail(event);
        event.setStatus(ESagaStatus.FAIL);
        event.setSource(CURRENT_SOURCE);
        addHistory(event, "Rollback executado na validação da Saga.");
        var sagaEventRequest = this.mapperIn.toSagaEventRequest(event);
        var payload = this.jsonUtil.toJson(sagaEventRequest);
        this.sagaEventOrchestratorOutputPort.sendEvent(payload);
        return event;
    }

    private void changeValidationToFail(SagaEvent event) {
        this.sagaEventFindOutputPort
            .findBySagaEventIdAndTransactionId(event.getSagaEventId(), event.getTransactionId())
            .ifPresentOrElse(validation -> {
                validation.setSuccess(false);
                this.sagaEventSaveOutputPort.save(validation);
            }, () -> {
                var validation = createValidationModel(event, false);
                this.sagaEventSaveOutputPort.save(validation);
            }
        );
    }
}

