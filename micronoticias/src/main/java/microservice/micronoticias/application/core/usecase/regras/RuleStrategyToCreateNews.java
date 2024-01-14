package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.application.core.domain.Noticia;

public interface RuleStrategyToCreateNews {

    void executar(Noticia noticia);
}

