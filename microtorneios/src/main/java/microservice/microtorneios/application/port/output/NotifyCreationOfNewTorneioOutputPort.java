package microservice.microtorneios.application.port.output;

import microservice.microtorneios.adapters.in.dto.response.TorneioSaveDto;
import microservice.microtorneios.application.core.domain.kafka.EventCreate;

public interface NotifyCreationOfNewTorneioOutputPort {

    void sendEvent(EventCreate eventCreate);
}

