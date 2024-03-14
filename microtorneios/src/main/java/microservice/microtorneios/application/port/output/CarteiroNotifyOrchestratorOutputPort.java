package microservice.microtorneios.application.port.output;

public interface SagaEventOrchestratorOutputPort {

    void sendEvent(String payload);
}

