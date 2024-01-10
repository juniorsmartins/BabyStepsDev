package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.port.input.NoticiaCriarInputPort;
import microservice.micronoticias.application.port.output.NoticiaSalvarOutputPort;

import java.util.Optional;

public class NoticiaCriarUseCase implements NoticiaCriarInputPort {

    private final NoticiaSalvarOutputPort cadastrarOutputPort;

    public NoticiaCriarUseCase(NoticiaSalvarOutputPort cadastrarOutputPort) {
        this.cadastrarOutputPort = cadastrarOutputPort;
    }

    @Override
    public Noticia criar(Noticia noticia) {

        return Optional.of(noticia)
            .map(this.cadastrarOutputPort::salvar)
            .orElseThrow();
    }
}

