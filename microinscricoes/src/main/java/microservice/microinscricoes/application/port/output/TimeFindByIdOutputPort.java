package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.Time;

import java.util.Optional;

public interface TimeFindByIdOutputPort {

    Optional<Time> findById(Long id);
}

