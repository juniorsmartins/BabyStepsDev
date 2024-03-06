package microservice.microtimes.application.port.output;

public interface SagaEventSendOrchestratorOutputPot {

    void sendEvent(String payload);
}

