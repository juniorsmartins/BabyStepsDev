package microservice.micronoticias.application.port.input;

import microservice.micronoticias.application.core.domain.Editoria;

public interface EditoriaUpdateInputPort {

    Editoria update(Editoria editoria);
}

