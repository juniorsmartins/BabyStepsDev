package microservice.micropagamentos.application.port.output;

public interface CarteiroNotifyOrchestratorOutputPort {

    void sendEvent(String payload);

}

