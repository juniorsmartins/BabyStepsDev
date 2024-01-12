package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategy;
import microservice.micronoticias.application.port.input.NoticiaCriarInputPort;
import microservice.micronoticias.application.port.output.NoticiaSalvarOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class NoticiaCriarUseCase implements NoticiaCriarInputPort {

    private final NoticiaSalvarOutputPort cadastrarOutputPort;

    @Autowired
    private List<RuleStrategy> ruleStrategies;

    public NoticiaCriarUseCase(NoticiaSalvarOutputPort cadastrarOutputPort) {
        this.cadastrarOutputPort = cadastrarOutputPort;
    }

    @Override
    public Noticia criar(Noticia noticia) {

        return Optional.of(noticia)
            .map(news -> {
                this.ruleStrategies.forEach(rule -> rule.executar(news));
                return news;
            })
            .map(this.cadastrarOutputPort::salvar)
            .orElseThrow();
    }
}

