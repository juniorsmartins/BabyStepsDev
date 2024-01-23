package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategyToUpdateNews;
import microservice.micronoticias.application.port.input.NoticiaUpdateInputPort;
import microservice.micronoticias.application.port.output.NoticiaUpdateOutputPort;
import microservice.micronoticias.config.exception.http_500.NoticiaUpdateUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class NoticiaUpdateUseCase implements NoticiaUpdateInputPort {

    private static final Logger log = LoggerFactory.getLogger(NoticiaUpdateUseCase.class);

    private final NoticiaUpdateOutputPort noticiaUpdateOutputPort;

    private final List<RuleStrategyToUpdateNews> ruleStrategies;

    public NoticiaUpdateUseCase(NoticiaUpdateOutputPort noticiaUpdateOutputPort,
                                List<RuleStrategyToUpdateNews> ruleStrategies) {
        this.noticiaUpdateOutputPort = noticiaUpdateOutputPort;
        this.ruleStrategies = ruleStrategies;
    }

    @Override
    public Noticia update(Noticia noticia) {

        log.info("Iniciado serviço para atualizar Notícia.");

        var response = Optional.ofNullable(noticia)
            .map(this::callBusinessRules)
            .map(this.noticiaUpdateOutputPort::update)
            .orElseThrow(NoticiaUpdateUseCaseException::new);

        log.info("Finalizado serviço para atualizar Notícia, com Id: {}.", response.getId());

        return response;
    }

    private Noticia callBusinessRules(Noticia noticia) {
        this.ruleStrategies.forEach(rule -> rule.executar(noticia));
        return noticia;
    }
}

