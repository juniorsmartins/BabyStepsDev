package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.Inscrito;

public interface InscritoSaveOutputPort {

    Inscrito save(Inscrito inscrito);
}

