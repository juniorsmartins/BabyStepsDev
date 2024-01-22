package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.port.input.NoticiaDeleteInputPort;
import microservice.micronoticias.application.port.output.NoticiaDeleteOutputPort;
import microservice.micronoticias.config.exception.http_500.NoticiaDeleteUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class NoticiaDeleteUseCase implements NoticiaDeleteInputPort {

    private static final Logger log = LoggerFactory.getLogger(NoticiaDeleteUseCase.class);

    private final NoticiaDeleteOutputPort noticiaDeleteOutputPort;

    public NoticiaDeleteUseCase(NoticiaDeleteOutputPort noticiaDeleteOutputPort) {
        this.noticiaDeleteOutputPort = noticiaDeleteOutputPort;
    }

    @Override
    public void deleteById(final Long id) {

        log.info("Iniciado serviço para deletar Notícia por Id.");

        Optional.ofNullable(id)
            .ifPresentOrElse(this.noticiaDeleteOutputPort::deleteById,
                () -> {throw new NoticiaDeleteUseCaseException();}
            );

        log.info("Finalizado serviço para deletar Notícia por id: {}.", id);
    }
}

