package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.SagaEvent;

public interface CarteiroNotifyStartOutputPort {

    void sendEvent(SagaEvent sagaEvent);
}

