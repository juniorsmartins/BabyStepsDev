package microservice.microtimes.application.port.input;

import microservice.microtimes.application.core.domain.SagaEvent;

public interface SagaEventSuccessInputPort {

    SagaEvent addTorneioInTime(SagaEvent sagaEvent);

}

