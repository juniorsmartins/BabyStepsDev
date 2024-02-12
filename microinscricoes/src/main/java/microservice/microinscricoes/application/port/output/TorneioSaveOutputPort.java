package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.Torneio;

public interface TorneioSaveOutputPort {

    Torneio save(Torneio torneio);
}

