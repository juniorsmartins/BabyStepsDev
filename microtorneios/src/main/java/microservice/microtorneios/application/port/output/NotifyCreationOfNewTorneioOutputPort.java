package microservice.microtorneios.application.port.output;

import microservice.microtorneios.application.core.domain.kafka.EventCreateTorneio;

public interface NotifyCreationOfNewTorneioOutputPort {

    void sendEvent(EventCreateTorneio eventCreate);
}

