package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.port.input.EditoriaUpdateInputPort;
import microservice.micronoticias.application.port.output.EditoriaUpdateOutputPort;
import microservice.micronoticias.config.exception.http_500.EditoriaUpdateUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class EditoriaUpdateUseCase implements EditoriaUpdateInputPort {

    private static final Logger log = LoggerFactory.getLogger(EditoriaUpdateUseCase.class);

    private final EditoriaUpdateOutputPort editoriaUpdateOutputPort;

    public EditoriaUpdateUseCase(EditoriaUpdateOutputPort editoriaUpdateOutputPort) {
        this.editoriaUpdateOutputPort = editoriaUpdateOutputPort;
    }

    @Override
    public Editoria update(final Editoria editoria) {

        log.info("Iniciado serviço para atualizar Editoria.");

        var response = Optional.ofNullable(editoria)
            .map(this.editoriaUpdateOutputPort::update)
            .orElseThrow(EditoriaUpdateUseCaseException::new);

        log.info("Finalizado serviço para atualizar Editoria, com Id: {}.", response.getId());

        return response;
    }
}

