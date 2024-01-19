package microservice.micronoticias.application.port.output;

import microservice.micronoticias.application.core.domain.Editoria;

import java.util.List;

public interface EditoriaListarOutputPort {

    List<Editoria> listar();
}

