package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.port.output.EditoriaBuscarPorNomenclaturaOutputPort;
import microservice.micronoticias.config.exception.http_409.NomenclaturaNaoUnicaException;
import microservice.micronoticias.config.exception.http_409.RuleWithProhibitedNullValueException;

import java.util.Optional;

public class RuleNomenclaturaUnicaDeEditoriaToCreateNews implements RuleStrategyToCreateNews, RuleStrategyToUpdateNews {

    private final EditoriaBuscarPorNomenclaturaOutputPort outputPort;

    public RuleNomenclaturaUnicaDeEditoriaToCreateNews(EditoriaBuscarPorNomenclaturaOutputPort editoriaBuscarPorNomenclaturaOutputPort) {
        this.outputPort = editoriaBuscarPorNomenclaturaOutputPort;
    }

    @Override
    public void executar(Noticia noticia) {

        Optional.ofNullable(noticia)
            .ifPresentOrElse(news ->
                news.getEditorias().forEach(editor ->
                    outputPort.buscarPorNomenclatura(editor.getNomenclatura())
                        .ifPresent(edit -> {
                            if (!edit.getId().equals(editor.getId())) {
                                throw new NomenclaturaNaoUnicaException(edit.getNomenclatura());
                            }
                        })
            ),
            () -> {throw new RuleWithProhibitedNullValueException("RuleNomenclaturaUnicaDeEditoria");}
        );
    }
}

