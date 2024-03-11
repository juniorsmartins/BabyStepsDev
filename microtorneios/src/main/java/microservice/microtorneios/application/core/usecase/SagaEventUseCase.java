package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.adapters.mapper.MapperIn;
import microservice.microtorneios.adapters.utils.JsonUtil;
import microservice.microtorneios.application.core.domain.SagaEvent;
import microservice.microtorneios.application.port.input.SagaEventInputPort;
import microservice.microtorneios.config.exception.http_409.SagaEventNullValueNotAllowedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class SagaEventUseCase implements SagaEventInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventUseCase.class);

    private static final String CURRENT_SOURCE = "TORNEIO-VALIDATION-SUCCESS";

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventUseCase(MapperIn mapperIn, JsonUtil jsonUtil) {
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public SagaEvent createValidation(SagaEvent sagaEvent) {

        log.info("Iniciado serviço para criar Success-Validation.");

//        var validationCreated = Optional.ofNullable(sagaEvent)
//            .map(event -> {
//
//                try {
//                    this.checkExistenceMandatoryValues(event);
//                    this.checkExistenceValidationDuplication(event);
//
//                } catch () {
//
//                }
//                return event;
//            })
//            .orElseThrow();

//        log.info("Finalizado serviço para criar Success-Validation: {}.", validationCreated);

        return sagaEvent;
    }

    private void checkExistenceMandatoryValues(SagaEvent event) {
        if (ObjectUtils.isEmpty(event.getSagaEventId()) || ObjectUtils.isEmpty(event.getTransactionId())) {
            throw new SagaEventNullValueNotAllowedException();
        }
    }

//    private void checkExistenceValidationDuplication(SagaEvent event) {
//        var exists = this.sagaEventExistsOutputPort.existsDuplication(event.getSagaEventId(), event.getTransactionId());
//        if (exists) {
//            throw new SagaEventValidationDuplicationException();
//        }
//    }

    @Override
    public SagaEvent rollbackEvent(SagaEvent event) {

        return null;
    }
}

