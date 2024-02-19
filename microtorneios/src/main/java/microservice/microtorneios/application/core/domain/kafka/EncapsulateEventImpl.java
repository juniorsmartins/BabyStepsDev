package microservice.microtorneios.application.core.domain.kafka;

import microservice.microtorneios.adapters.in.dto.response.TorneioSaveDto;

public class EncapsulateEventImpl implements EncapsulateEvent {

    @Override
    public EventCreate toEventCreate(TorneioSaveDto torneioSaveDto) {
        return new EventCreate(torneioSaveDto);
    }
}

