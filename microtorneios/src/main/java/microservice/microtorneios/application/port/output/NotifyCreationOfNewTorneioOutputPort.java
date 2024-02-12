package microservice.microtorneios.application.port.output;

public interface NotifyCreationOfNewTorneioOutputPort {

    void sendEvent(String payload);
}

