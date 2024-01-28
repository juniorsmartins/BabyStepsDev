package microservice.microtorneios.application.port.input;

import microservice.microtorneios.application.core.domain.Torneio;

public interface TorneioCreateInputPort {

    Torneio create(Torneio torneio);
}

