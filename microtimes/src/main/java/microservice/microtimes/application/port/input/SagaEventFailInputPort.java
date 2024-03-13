package microservice.microtimes.application.port.input;

import microservice.microtimes.application.core.domain.SagaEvent;

public interface SagaEventFailInputPort {

    SagaEvent rollbackEvent(SagaEvent event);

}

