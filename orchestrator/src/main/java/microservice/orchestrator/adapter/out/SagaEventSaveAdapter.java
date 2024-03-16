package microservice.orchestrator.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.port.output.SagaEventSaveOutputPort;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SagaEventSaveAdapter implements SagaEventSaveOutputPort {

    @Override
    public SagaEvent create(SagaEvent sagaEvent) {
        return null;
    }

}

