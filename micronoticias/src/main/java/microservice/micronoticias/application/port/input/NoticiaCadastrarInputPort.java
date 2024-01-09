package microservice.micronoticias.application.port.input;

import microservice.micronoticias.application.core.domain.Noticia;

public interface NoticiaCadastrarInputPort {

    Noticia cadastrar(Noticia noticia);
}

