package microservice.microtorneios.application.port.input;

import microservice.microtorneios.application.core.domain.SagaEvent;

public interface SagaEventSuccessInputPort {

    SagaEvent addTimeInTorneio(SagaEvent sagaEvent);

}

