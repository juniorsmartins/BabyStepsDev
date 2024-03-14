package microservice.microtimes.application.port.output;

public interface CarteiroNotifyOrchestratorOutputPort {

    void sendEvent(String payload);
}

