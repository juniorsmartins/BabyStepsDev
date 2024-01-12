package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategy;
import microservice.micronoticias.application.port.input.NoticiaCriarInputPort;
import microservice.micronoticias.application.port.output.NoticiaSalvarOutputPort;

import java.util.List;
import java.util.Optional;

public class NoticiaCriarUseCase implements NoticiaCriarInputPort {

    private final NoticiaSalvarOutputPort cadastrarOutputPort;

    private final List<RuleStrategy> ruleStrategies;

    public NoticiaCriarUseCase(NoticiaSalvarOutputPort cadastrarOutputPort, List<RuleStrategy> ruleStrategies) {
        this.cadastrarOutputPort = cadastrarOutputPort;
        this.ruleStrategies = ruleStrategies;
    }

    @Override
    public Noticia criar(Noticia noticia) {

        this.ruleStrategies.forEach(rule -> rule.executar(noticia));
        return this.cadastrarOutputPort.salvar(noticia);
    }
}

