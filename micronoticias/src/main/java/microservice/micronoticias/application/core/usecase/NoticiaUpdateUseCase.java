package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.port.input.NoticiaUpdateInputPort;
import microservice.micronoticias.application.port.output.NoticiaUpdateOutputPort;

public class NoticiaUpdateUseCase implements NoticiaUpdateInputPort {

    private final NoticiaUpdateOutputPort noticiaUpdateOutputPort;

    public NoticiaUpdateUseCase(NoticiaUpdateOutputPort noticiaUpdateOutputPort) {
        this.noticiaUpdateOutputPort = noticiaUpdateOutputPort;
    }

    @Override
    public Noticia update(Noticia noticia) {

        return null;
    }
}

