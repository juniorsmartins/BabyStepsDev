package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.port.output.EditoriaBuscarPorNomenclaturaOutputPort;
import microservice.micronoticias.config.exception.http_409.NomenclaturaNaoUnicaException;
import microservice.micronoticias.config.exception.http_409.RuleWithProhibitedNullValueException;

import java.util.Optional;

public class SingleNamingRuleForEditorial implements RuleStrategyToUpdateEditor {

    private final EditoriaBuscarPorNomenclaturaOutputPort outputPort;

    public SingleNamingRuleForEditorial(EditoriaBuscarPorNomenclaturaOutputPort editoriaBuscarPorNomenclaturaOutputPort) {
        this.outputPort = editoriaBuscarPorNomenclaturaOutputPort;
    }

    @Override
    public void executar(Editoria editoriaAtual) {

        Optional.ofNullable(editoriaAtual)
            .ifPresentOrElse(atual ->
                this.outputPort.buscarPorNomenclatura(atual.getNomenclatura())
                    .ifPresent(editoriaExistente -> checkSingularity(editoriaExistente, atual)),
            () -> {throw new RuleWithProhibitedNullValueException("SingleNamingRuleForEditorial");}
        );
    }

    private void checkSingularity(Editoria editoriaExistente, Editoria editoriaAtual) {
        if (!editoriaExistente.getId().equals(editoriaAtual.getId())) {
            throw new NomenclaturaNaoUnicaException(editoriaExistente.getNomenclatura());
        }
    }
}

