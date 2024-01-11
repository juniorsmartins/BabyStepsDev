package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.application.core.domain.Noticia;

public interface RuleChainOfResponsibility {

    Noticia execute(Noticia noticia);
}

