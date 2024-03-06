package microservice.microtimes.application.core.usecase;

import microservice.microtimes.application.core.domain.SagaEvent;
import microservice.microtimes.application.core.domain.ValidationModel;
import microservice.microtimes.application.port.input.SagaEventSuccessValidationInputPort;
import microservice.microtimes.application.port.output.SagaEventSaveSuccessValidationOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class SagaEventSuccessValidationUseCase implements SagaEventSuccessValidationInputPort {

    private final SagaEventSaveSuccessValidationOutputPort sagaEventSaveSuccessValidationOutputPort;

    public SagaEventSuccessValidationUseCase(SagaEventSaveSuccessValidationOutputPort sagaEventSaveSuccessValidationOutputPort) {
        this.sagaEventSaveSuccessValidationOutputPort = sagaEventSaveSuccessValidationOutputPort;
    }

    private static final Logger log = LoggerFactory.getLogger(SagaEventSuccessValidationUseCase.class);

    @Override
    public ValidationModel createSuccessValidation(SagaEvent sagaEvent) {

        log.info("Iniciado serviço para criar Success-Validation.");

        var validationCreated = Optional.ofNullable(sagaEvent)
            .map(ValidationModel::transformSagaEventInValidation)
            .map(this.sagaEventSaveSuccessValidationOutputPort::saveSuccessValidation)
            .orElseThrow();

        log.info("Finalizado serviço para criar Success-Validation: {}.", validationCreated);

        return validationCreated;
    }
}

