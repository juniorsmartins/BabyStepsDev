package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.domain.Noticia;

public class RuleNoticiaUnica implements RuleStrategy {

    private final NoticiaRepository noticiaRepository;

    public RuleNoticiaUnica(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    @Override
    public void executar(Noticia noticia) {

    }
}
