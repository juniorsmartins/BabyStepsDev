package microservice.microtorneios.application.core.domain.kafka;

import microservice.microtorneios.adapters.in.dto.response.TorneioSaveDto;

public class EncapsulateEventImpl implements EncapsulateEvent {

    @Override
    public EventCreateTorneio toEventCreateTorneio(TorneioSaveDto torneioSaveDto) {
        return new EventCreateTorneio(torneioSaveDto);
    }
}

