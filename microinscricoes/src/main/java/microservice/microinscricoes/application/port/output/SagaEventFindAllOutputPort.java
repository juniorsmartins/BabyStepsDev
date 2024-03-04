package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.SagaEvent;

import java.util.List;

public interface SagaEventFindAllOutputPort {

    List<SagaEvent> findAll();
}

