package microservice.microinscricoes.application.port.input;

import microservice.microinscricoes.application.core.domain.Inscrito;

public interface InscritoCreateInputPort {

    Inscrito create(Inscrito inscrito);
}

