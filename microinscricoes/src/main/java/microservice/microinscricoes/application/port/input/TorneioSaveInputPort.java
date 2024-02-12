package microservice.microinscricoes.application.port.input;

import microservice.microinscricoes.application.core.domain.Torneio;

public interface TorneioSaveInputPort {

    Torneio save(Torneio torneio);
}

