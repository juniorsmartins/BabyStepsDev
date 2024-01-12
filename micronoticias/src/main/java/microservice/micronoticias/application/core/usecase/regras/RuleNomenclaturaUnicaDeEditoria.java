package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.config.exception.http_409.NomenclaturaNaoUnicaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RuleNomenclaturaUnicaDeEditoria implements RuleStrategy {

    @Autowired
    private EditoriaRepository editoriaRepository;

    @Override
    public void executar(Noticia noticia) {
        noticia.getEditorias().forEach(editor ->
            this.editoriaRepository.findByNomenclatura(editor.getNomenclatura())
                .ifPresent(edit -> {
                    if (!edit.getId().equals(editor.getId())) {
                        throw new NomenclaturaNaoUnicaException(edit.getNomenclatura());
                    }
                })
        );
    }
}

