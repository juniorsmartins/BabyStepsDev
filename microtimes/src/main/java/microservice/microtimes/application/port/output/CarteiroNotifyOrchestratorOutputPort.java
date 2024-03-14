package microservice.microtimes.application.port.output;

public interface SagaEventOrchestratorOutputPort {

    void sendEvent(String payload);
}

