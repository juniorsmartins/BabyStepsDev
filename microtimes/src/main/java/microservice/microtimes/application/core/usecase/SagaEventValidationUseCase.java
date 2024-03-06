package microservice.microtimes.application.core.usecase;

import microservice.microtimes.adapter.mapper.MapperIn;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.core.domain.History;
import microservice.microtimes.application.core.domain.SagaEvent;
import microservice.microtimes.application.core.domain.ValidationModel;
import microservice.microtimes.application.core.domain.enums.ESagaStatus;
import microservice.microtimes.application.port.input.SagaEventValidationInputPort;
import microservice.microtimes.application.port.output.SagaEventExistsOutputPort;
import microservice.microtimes.application.port.output.SagaEventFindOutputPort;
import microservice.microtimes.application.port.output.SagaEventSaveValidationOutputPort;
import microservice.microtimes.application.port.output.SagaEventSendOrchestratorOutputPot;
import microservice.microtimes.config.exception.http_409.SagaEventNullValueNotAllowedException;
import microservice.microtimes.config.exception.http_409.SuccessValidationDuplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.Optional;

public class SagaEventValidationUseCase implements SagaEventValidationInputPort {

    private static final String CURRENT_SOURCE = "TIME-VALIDATION-SUCCESS";

    private final SagaEventExistsOutputPort sagaEventExistsOutputPort;

    private final SagaEventSaveValidationOutputPort sagaEventSaveValidationOutputPort;

    private final SagaEventSendOrchestratorOutputPot sagaEventSendOrchestratorOutputPot;

    private final SagaEventFindOutputPort sagaEventFindOutputPort;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventValidationUseCase(SagaEventExistsOutputPort sagaEventExistsOutputPort,
                                      SagaEventSaveValidationOutputPort sagaEventSaveValidationOutputPort,
                                      SagaEventSendOrchestratorOutputPot sagaEventSendOrchestratorOutputPot,
                                      SagaEventFindOutputPort sagaEventFindOutputPort,
                                      MapperIn mapperIn,
                                      JsonUtil jsonUtil) {
        this.sagaEventExistsOutputPort = sagaEventExistsOutputPort;
        this.sagaEventSaveValidationOutputPort = sagaEventSaveValidationOutputPort;
        this.sagaEventSendOrchestratorOutputPot = sagaEventSendOrchestratorOutputPot;
        this.sagaEventFindOutputPort = sagaEventFindOutputPort;
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
    }

    private static final Logger log = LoggerFactory.getLogger(SagaEventValidationUseCase.class);

    @Override
    public void createValidation(SagaEvent sagaEvent) {

        log.info("Iniciado serviço para criar Success-Validation.");

        var validationCreated = Optional.ofNullable(sagaEvent)
            .map(event -> {
                try {
                    this.checkExistenceMandatoryValues(event);
                    this.checkExistenceValidationDuplication(event);
                    var model = this.createValidationModel(event, true);
                    this.sagaEventSaveValidationOutputPort.save(model);
                    handleSuccess(event);

                } catch (SagaEventNullValueNotAllowedException | SuccessValidationDuplicationException ex) {
                    log.error("Erro: {}", ex.getMessage(), ex);
                    handleFailCurrentNotExecuted(sagaEvent, ex.getMessage());
                }
                this.sagaEventSendOrchestratorOutputPot.sendEvent(this.jsonUtil.toJson(event));
                return event;
            })
            .orElseThrow();

        log.info("Finalizado serviço para criar Success-Validation: {}.", validationCreated);
    }

    private void checkExistenceMandatoryValues(SagaEvent event) {
        if (ObjectUtils.isEmpty(event.getSagaEventId()) || ObjectUtils.isEmpty(event.getTransactionId())) {
            throw new SagaEventNullValueNotAllowedException();
        }
    }

    private void checkExistenceValidationDuplication(SagaEvent event) {
        var exists = this.sagaEventExistsOutputPort.existsDuplication(event.getSagaEventId(), event.getTransactionId());
        if (exists) {
            throw new SuccessValidationDuplicationException();
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
        addHistory(event, message);
    }

    @Override
    public void rollbackEvent(SagaEvent event) {
        this.changeValidationToFail(event);
        event.setStatus(ESagaStatus.FAIL);
        event.setSource(CURRENT_SOURCE);
        addHistory(event, "Rollback executado na validação da Saga.");
        var sagaEventRequest = this.mapperIn.toSagaEventRequest(event);
        var payload = this.jsonUtil.toJson(sagaEventRequest);
        this.sagaEventSendOrchestratorOutputPot.sendEvent(payload);
    }

    private void changeValidationToFail(SagaEvent event) {
        this.sagaEventFindOutputPort
            .findBySagaEventIdAndTransactionId(event.getSagaEventId(), event.getTransactionId())
            .ifPresentOrElse(validation -> {
                validation.setSuccess(false);
                this.sagaEventSaveValidationOutputPort.save(validation);
            }, () -> {
                var validation = createValidationModel(event, false);
                this.sagaEventSaveValidationOutputPort.save(validation);
            }
        );
    }
}

