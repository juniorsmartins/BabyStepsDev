package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.Inscricao;

import java.util.Optional;

public interface InscricaoFindByIdOutputPort {

    Optional<Inscricao> findById(Long id);
}

