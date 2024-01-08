package microservice.micronoticias.application.port.output;

import microservice.micronoticias.application.core.domain.Noticia;

public interface NoticiaSalvarOutputPort {

    Noticia salvar(Noticia noticia);
}

