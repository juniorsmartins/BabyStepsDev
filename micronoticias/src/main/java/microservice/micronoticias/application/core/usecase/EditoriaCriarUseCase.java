package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategyToCreateEditor;
import microservice.micronoticias.application.port.input.EditoriaCriarInputPort;
import microservice.micronoticias.application.port.output.EditoriaBuscarPorNomenclaturaOutputPort;
import microservice.micronoticias.application.port.output.EditoriaSalvarOutputPort;
import microservice.micronoticias.config.exception.http_500.EditoriaCriarUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class EditoriaCriarUseCase implements EditoriaCriarInputPort {

    private static final Logger log = LoggerFactory.getLogger(EditoriaCriarUseCase.class);

    private final EditoriaSalvarOutputPort editoriaSalvarOutputPort;

    private final EditoriaBuscarPorNomenclaturaOutputPort editoriaBuscarPorNomenclaturaOutputPort;

    private final List<RuleStrategyToCreateEditor> ruleStrategies;

    public EditoriaCriarUseCase(EditoriaSalvarOutputPort editoriaSalvarOutputPort,
                                EditoriaBuscarPorNomenclaturaOutputPort editoriaBuscarPorNomenclaturaOutputPort,
                                List<RuleStrategyToCreateEditor> ruleStrategies) {
        this.editoriaSalvarOutputPort = editoriaSalvarOutputPort;
        this.editoriaBuscarPorNomenclaturaOutputPort = editoriaBuscarPorNomenclaturaOutputPort;
        this.ruleStrategies = ruleStrategies;
    }

    @Override
    public Editoria criar(Editoria editoria) {

        log.info("Iniciado serviço para cadastrar nova Editoria.");

        var resposta = Optional.ofNullable(editoria)
            .map(this::callBusinessRules)
            .map(this.editoriaSalvarOutputPort::salvar)
            .orElseThrow(EditoriaCriarUseCaseException::new);

        log.info("Finalizado serviço para cadastrar nova Editoria, com nomenclatura: {}.", resposta.getNomenclatura());

        return resposta;
    }

    private Editoria callBusinessRules(Editoria editoria) {
        this.ruleStrategies.forEach(rule -> rule.executar(editoria));
        return editoria;
    }
}

