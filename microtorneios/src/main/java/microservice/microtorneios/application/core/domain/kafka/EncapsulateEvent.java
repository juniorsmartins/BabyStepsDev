package microservice.microtorneios.application.core.domain.kafka;

import microservice.microtorneios.adapters.in.dto.response.TorneioSaveDto;

public interface EncapsulateEvent {

    EventCreate toEventCreate(TorneioSaveDto torneioSaveDto);
}

