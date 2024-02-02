package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.Inscricao;

public interface InscricaoSaveOutputPort {

    Inscricao save(Inscricao inscricao);
}

