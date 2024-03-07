package microservice.micropagamentos.application.port.output;

public interface SagaEventSendOrchestratorOutputPot {

    void sendEvent(String payload);
}

