package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.port.input.EditoriaCriarInputPort;
import microservice.micronoticias.application.port.output.EditoriaBuscarPorNomenclaturaOutputPort;
import microservice.micronoticias.application.port.output.EditoriaSalvarOutputPort;
import microservice.micronoticias.config.exception.http_409.NomenclaturaNaoUnicaException;
import microservice.micronoticias.config.exception.http_409.RuleWithProhibitedNullValueException;
import microservice.micronoticias.config.exception.http_500.EditoriaCriarUseCaseException;

import java.util.Optional;

public class EditoriaCriarUseCase implements EditoriaCriarInputPort {

    private final EditoriaSalvarOutputPort editoriaSalvarOutputPort;

    private final EditoriaBuscarPorNomenclaturaOutputPort editoriaBuscarPorNomenclaturaOutputPort;

    public EditoriaCriarUseCase(EditoriaSalvarOutputPort editoriaSalvarOutputPort,
                                EditoriaBuscarPorNomenclaturaOutputPort editoriaBuscarPorNomenclaturaOutputPort) {
        this.editoriaSalvarOutputPort = editoriaSalvarOutputPort;
        this.editoriaBuscarPorNomenclaturaOutputPort = editoriaBuscarPorNomenclaturaOutputPort;
    }

    @Override
    public Editoria criar(Editoria editoria) {

        return Optional.ofNullable(editoria)
            .map(this::callBusinessRules)
            .map(this.editoriaSalvarOutputPort::salvar)
            .orElseThrow(EditoriaCriarUseCaseException::new);
    }

    public Editoria callBusinessRules(Editoria editoria) {

        Optional.ofNullable(editoria)
            .ifPresentOrElse(edit -> {
                var editoriaEncontrada = this.editoriaBuscarPorNomenclaturaOutputPort.buscarPorNomenclatura(edit.getNomenclatura());
                if (editoriaEncontrada.isPresent() && !editoriaEncontrada.get().getId().equals(edit.getId())) {
                    throw new NomenclaturaNaoUnicaException(edit.getNomenclatura());
                }
            },
            () -> {throw new RuleWithProhibitedNullValueException("NomenclaturaÚnicaDeEditoria");}
        );

        return editoria;
    }
}

