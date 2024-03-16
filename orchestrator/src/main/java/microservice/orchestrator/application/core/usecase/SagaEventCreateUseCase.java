package microservice.orchestrator.application.core.usecase;

import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.port.input.SagaEventCreateInputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SagaEventCreateUseCase implements SagaEventCreateInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventCreateUseCase.class);

    @Override
    public SagaEvent create(SagaEvent sagaEvent) {
        return null;
    }

}

