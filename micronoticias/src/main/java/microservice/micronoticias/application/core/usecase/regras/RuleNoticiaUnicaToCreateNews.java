package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.port.output.NoticiaBuscarPorTituloOutputPort;
import microservice.micronoticias.config.exception.http_409.NoticiaRepetidaException;
import microservice.micronoticias.config.exception.http_409.RuleWithProhibitedNullValueException;

import java.util.Optional;

public class RuleNoticiaUnicaToCreateNews implements RuleStrategyToCreateNews {

    private final NoticiaBuscarPorTituloOutputPort outputPort;

    public RuleNoticiaUnicaToCreateNews(NoticiaBuscarPorTituloOutputPort noticiaBuscarPorTituloOutputPort) {
        this.outputPort = noticiaBuscarPorTituloOutputPort;
    }

    @Override
    public void executar(Noticia noticia) {

        Optional.ofNullable(noticia)
            .ifPresentOrElse(news ->
                outputPort.buscarPorTitulo(news.getTitulo())
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
