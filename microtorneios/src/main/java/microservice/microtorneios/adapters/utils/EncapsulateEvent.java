package microservice.microtorneios.adapters.utils;

import microservice.microtorneios.adapters.out.producer.event.EventCreateTorneio;
import microservice.microtorneios.application.core.domain.Torneio;

public interface EncapsulateEvent {

    EventCreateTorneio toEventCreateTorneio(Torneio torneio);
}

