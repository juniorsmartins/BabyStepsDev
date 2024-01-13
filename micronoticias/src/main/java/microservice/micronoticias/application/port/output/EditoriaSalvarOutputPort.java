package microservice.micronoticias.application.port.output;

import microservice.micronoticias.application.core.domain.Editoria;

public interface EditoriaSalvarOutputPort {

    Editoria salvar(Editoria editoria);
}

