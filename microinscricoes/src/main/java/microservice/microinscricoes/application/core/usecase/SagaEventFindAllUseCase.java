package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.SagaEvent;
import microservice.microinscricoes.application.port.input.SagaEventFindAllInputPort;
import microservice.microinscricoes.application.port.output.SagaEventFindAllOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SagaEventFindAllUseCase implements SagaEventFindAllInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventFindAllUseCase.class);

    private final SagaEventFindAllOutputPort sagaEventFindAllOutputPort;

    public SagaEventFindAllUseCase(SagaEventFindAllOutputPort sagaEventFindAllOutputPort) {
        this.sagaEventFindAllOutputPort = sagaEventFindAllOutputPort;
    }

    @Override
    public List<SagaEvent> findAll() {

        log.info("Iniciado serviço para buscar lista de SagaEvent.");

        var sagaList = this.sagaEventFindAllOutputPort.findAll();

        log.info("Finalizado serviço para buscar lista de SagaEvent {}.", sagaList);

        return sagaList;
    }

}

