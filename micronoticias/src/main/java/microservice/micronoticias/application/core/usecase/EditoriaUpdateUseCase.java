package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategyToCreateNews;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategyToUpdateEditor;
import microservice.micronoticias.application.port.input.EditoriaUpdateInputPort;
import microservice.micronoticias.application.port.output.EditoriaUpdateOutputPort;
import microservice.micronoticias.config.exception.http_500.EditoriaUpdateUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class EditoriaUpdateUseCase implements EditoriaUpdateInputPort {

    private static final Logger log = LoggerFactory.getLogger(EditoriaUpdateUseCase.class);

    private final EditoriaUpdateOutputPort editoriaUpdateOutputPort;

    private final List<RuleStrategyToUpdateEditor> ruleStrategies;

    public EditoriaUpdateUseCase(EditoriaUpdateOutputPort editoriaUpdateOutputPort,
                                 List<RuleStrategyToUpdateEditor> ruleStrategies) {
        this.editoriaUpdateOutputPort = editoriaUpdateOutputPort;
        this.ruleStrategies = ruleStrategies;
    }

    @Override
    public Editoria update(final Editoria editoria) {

        log.info("Iniciado serviço para atualizar Editoria.");

        var response = Optional.ofNullable(editoria)
            .map(this::callBusinessRules)
            .map(this.editoriaUpdateOutputPort::update)
            .orElseThrow(EditoriaUpdateUseCaseException::new);

        log.info("Finalizado serviço para atualizar Editoria, com Id: {}.", response.getId());

        return response;
    }

    private Editoria callBusinessRules(Editoria editoria) {
        this.ruleStrategies.forEach(rule -> rule.executar(editoria));
        return editoria;
    }
}

