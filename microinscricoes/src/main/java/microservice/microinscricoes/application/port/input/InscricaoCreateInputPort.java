package microservice.microinscricoes.application.port.input;

import microservice.microinscricoes.application.core.domain.Inscricao;

public interface InscricaoCreateInputPort {

    Inscricao create(Inscricao inscricao);
}

