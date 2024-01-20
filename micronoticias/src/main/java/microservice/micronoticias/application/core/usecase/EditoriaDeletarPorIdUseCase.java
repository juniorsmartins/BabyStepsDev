package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.port.input.EditoriaDeletarPorIdInputPort;
import microservice.micronoticias.application.port.output.EditoriaDeletarPorIdOutputPort;
import microservice.micronoticias.config.exception.http_500.EditoriaDeletarPorIdUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class EditoriaDeletarPorIdUseCase implements EditoriaDeletarPorIdInputPort {

    private static final Logger log = LoggerFactory.getLogger(EditoriaDeletarPorIdUseCase.class);

    private final EditoriaDeletarPorIdOutputPort editoriaDeletarPorIdOutputPort;

    public EditoriaDeletarPorIdUseCase(EditoriaDeletarPorIdOutputPort editoriaDeletarPorIdOutputPort) {
        this.editoriaDeletarPorIdOutputPort = editoriaDeletarPorIdOutputPort;
    }

    @Override
    public void deletarPorId(final Long id) {

        log.info("Iniciado serviço para deletar Editoria por Id.");

        Optional.ofNullable(id)
            .ifPresentOrElse(this.editoriaDeletarPorIdOutputPort::deletarPorId,
                () -> {throw new EditoriaDeletarPorIdUseCaseException();}
            );

        log.info("Finalizado serviço para deletar Editoria por id: {}.", id);
    }
}

