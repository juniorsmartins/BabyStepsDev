package microservice.micronoticias.application.port.input;

import microservice.micronoticias.application.core.domain.Editoria;

public interface EditoriaCriarInputPort {

    Editoria criar(Editoria editoria);
}

