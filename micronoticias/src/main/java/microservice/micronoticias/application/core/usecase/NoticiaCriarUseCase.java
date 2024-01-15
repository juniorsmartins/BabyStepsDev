package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategyToCreateNews;
import microservice.micronoticias.application.port.input.NoticiaCriarInputPort;
import microservice.micronoticias.application.port.output.NoticiaSalvarOutputPort;
import microservice.micronoticias.config.exception.http_500.NoticiaCriarUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class NoticiaCriarUseCase implements NoticiaCriarInputPort {

    private static final Logger log = LoggerFactory.getLogger(NoticiaCriarUseCase.class);

    private final NoticiaSalvarOutputPort cadastrarOutputPort;

    private final List<RuleStrategyToCreateNews> ruleStrategies;

    public NoticiaCriarUseCase(NoticiaSalvarOutputPort cadastrarOutputPort, List<RuleStrategyToCreateNews> ruleStrategies) {
        this.cadastrarOutputPort = cadastrarOutputPort;
        this.ruleStrategies = ruleStrategies;
    }

    @Override
    public Noticia criar(Noticia noticia) {

        log.info("Iniciado serviço para cadastrar nova Notícia.");

        var resposta = Optional.ofNullable(noticia)
            .map(this::callBusinessRules)
            .map(this.cadastrarOutputPort::salvar)
            .orElseThrow(NoticiaCriarUseCaseException::new);

        log.info("Finalizado serviço para cadastrar nova Notícia, com título: {}.", resposta.getTitulo());

        return resposta;
    }

    private Noticia callBusinessRules(Noticia noticia) {
        this.ruleStrategies.forEach(rule -> rule.executar(noticia));
        return noticia;
    }
}

