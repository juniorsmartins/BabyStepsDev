package microservice.microinscricoes.application.port.input;

import microservice.microinscricoes.application.core.domain.SagaEvent;

import java.util.List;

public interface SagaEventFindAllInputPort {

    List<SagaEvent> findAllByCreatedAtDesc();
}

