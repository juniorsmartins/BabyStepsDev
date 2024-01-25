package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.port.input.EditoriaDeletarPorIdInputPort;
import microservice.micronoticias.application.port.output.EditoriaDeletarPorIdOutputPort;
import microservice.micronoticias.application.port.output.EditoriaInUseOutputPort;
import microservice.micronoticias.config.exception.http_409.EditorialInUseException;
import microservice.micronoticias.config.exception.http_500.EditoriaDeletarPorIdUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class EditoriaDeletarPorIdUseCase implements EditoriaDeletarPorIdInputPort {

    private static final Logger log = LoggerFactory.getLogger(EditoriaDeletarPorIdUseCase.class);

    private final EditoriaDeletarPorIdOutputPort editoriaDeletarPorIdOutputPort;

    private final EditoriaInUseOutputPort editoriaInUseOutputPort;

    public EditoriaDeletarPorIdUseCase(EditoriaDeletarPorIdOutputPort editoriaDeletarPorIdOutputPort,
                                       EditoriaInUseOutputPort editoriaInUseOutputPort) {
        this.editoriaDeletarPorIdOutputPort = editoriaDeletarPorIdOutputPort;
        this.editoriaInUseOutputPort = editoriaInUseOutputPort;
    }

    @Override
    public void deletarPorId(final Long id) {

        log.info("Iniciado serviço para deletar Editoria por Id.");

        Optional.ofNullable(id)
            .ifPresentOrElse(key -> {
                    this.checkInUse(key);
                    this.editoriaDeletarPorIdOutputPort.deletarPorId(key);
                },
                () -> {throw new EditoriaDeletarPorIdUseCaseException();}
            );

        log.info("Finalizado serviço para deletar Editoria por id: {}.", id);
    }

    private void checkInUse(final Long id) {
        var inUse = this.editoriaInUseOutputPort.inUse(id);
        if (inUse) {
            throw new EditorialInUseException(id);
        }
    }
}

