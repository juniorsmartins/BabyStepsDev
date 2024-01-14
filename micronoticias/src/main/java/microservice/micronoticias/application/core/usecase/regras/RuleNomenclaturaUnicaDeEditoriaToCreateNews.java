package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.config.exception.http_409.NomenclaturaNaoUnicaException;
import microservice.micronoticias.config.exception.http_409.RuleWithProhibitedNullValueException;

import java.util.Optional;

public class RuleNomenclaturaUnicaDeEditoriaToCreateNews implements RuleStrategyToCreateNews {

    private final EditoriaRepository editoriaRepository;

    public RuleNomenclaturaUnicaDeEditoriaToCreateNews(EditoriaRepository editoriaRepository) {
        this.editoriaRepository = editoriaRepository;
    }

    @Override
    public void executar(Noticia noticia) {

        Optional.ofNullable(noticia)
            .ifPresentOrElse(news ->
                news.getEditorias().forEach(editor ->
                    this.editoriaRepository.findByNomenclatura(editor.getNomenclatura())
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

