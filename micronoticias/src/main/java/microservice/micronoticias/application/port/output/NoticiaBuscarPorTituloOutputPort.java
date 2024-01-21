package microservice.micronoticias.application.port.output;

import microservice.micronoticias.application.core.domain.Noticia;

import java.util.Optional;

public interface NoticiaBuscarPorTituloOutputPort {

    Optional<Noticia> buscarPorTitulo(String titulo);
}

