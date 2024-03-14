package microservice.microtorneios.application.port.output;

public interface CarteiroNotifyOrchestratorOutputPort {

    void sendEvent(String payload);
}

