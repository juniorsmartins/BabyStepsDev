package microservice.microtorneios.application.port.input;

import microservice.microtorneios.application.core.domain.EventFindIdTorneio;

public interface FindIdTorneioInputPort {

    EventFindIdTorneio findId(EventFindIdTorneio eventFindIdTorneio);
}

