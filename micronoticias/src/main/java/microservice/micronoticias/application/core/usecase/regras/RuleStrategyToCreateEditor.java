package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.application.core.domain.Editoria;

public interface RuleStrategyToCreateEditor {

    void executar(Editoria editoria);
}

