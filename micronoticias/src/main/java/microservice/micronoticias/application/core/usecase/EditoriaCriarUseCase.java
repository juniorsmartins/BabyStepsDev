package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.port.input.EditoriaCriarInputPort;
import microservice.micronoticias.application.port.output.EditoriaSalvarOutputPort;
import microservice.micronoticias.config.exception.http_500.EditoriaCriarUseCaseException;

import java.util.Optional;

public class EditoriaCriarUseCase implements EditoriaCriarInputPort {

    private final EditoriaSalvarOutputPort editoriaSalvarOutputPort;

    public EditoriaCriarUseCase(EditoriaSalvarOutputPort editoriaSalvarOutputPort) {
        this.editoriaSalvarOutputPort = editoriaSalvarOutputPort;
    }

    @Override
    public Editoria criar(Editoria editoria) {

        return Optional.ofNullable(editoria)
            .map(this.editoriaSalvarOutputPort::salvar)
            .orElseThrow(EditoriaCriarUseCaseException::new);
    }
}

