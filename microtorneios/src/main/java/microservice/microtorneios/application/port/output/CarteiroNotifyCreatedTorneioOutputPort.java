package microservice.microtorneios.application.port.output;

import microservice.microtorneios.application.core.domain.Torneio;

public interface CarteiroNotifyCreatedTorneioOutputPort {

    void sendEvent(Torneio torneio);
}

