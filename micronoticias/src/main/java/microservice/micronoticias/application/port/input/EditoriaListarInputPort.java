package microservice.micronoticias.application.port.input;

import microservice.micronoticias.application.core.domain.Editoria;

import java.util.List;

public interface EditoriaListarInputPort {

    List<Editoria> listar();
}

