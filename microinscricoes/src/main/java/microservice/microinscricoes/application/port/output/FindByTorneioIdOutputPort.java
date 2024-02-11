package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.Torneio;

import java.util.Optional;

public interface FindByTorneioIdOutputPort {

    Optional<Torneio> findById(Long torneioId);
}

