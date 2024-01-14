package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.config.exception.http_409.NoticiaRepetidaException;
import microservice.micronoticias.config.exception.http_409.RuleWithProhibitedNullValueException;

import java.util.Optional;

public class RuleNoticiaUnicaToCreateNews implements RuleStrategyToCreateNews {

    private final NoticiaRepository noticiaRepository;

    public RuleNoticiaUnicaToCreateNews(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    @Override
    public void executar(Noticia noticia) {

        Optional.ofNullable(noticia)
            .ifPresentOrElse(news ->
                this.noticiaRepository.findByTitulo(news.getTitulo())
                    .ifPresent(paper -> {
                        if (!paper.getId().equals(news.getId()) && news.getChapeu().equals(paper.getChapeu())
                            && news.getLinhaFina().equals(paper.getLinhaFina())) {
                            throw new NoticiaRepetidaException(news.getTitulo());
                        }
                    }),
            () -> {throw new RuleWithProhibitedNullValueException("RuleNoticiaUnica");
            }
        );
    }
}
