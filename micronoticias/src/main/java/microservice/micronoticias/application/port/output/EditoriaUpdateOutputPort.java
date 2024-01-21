package microservice.micronoticias.application.port.output;

import microservice.micronoticias.application.core.domain.Editoria;

public interface EditoriaUpdateOutputPort {

    Editoria update(Editoria editoria);
}

