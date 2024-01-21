package microservice.micronoticias.config.bean;

import microservice.micronoticias.adapter.out.*;
import microservice.micronoticias.application.core.usecase.EditoriaCriarUseCase;
import microservice.micronoticias.application.core.usecase.EditoriaDeletarPorIdUseCase;
import microservice.micronoticias.application.core.usecase.EditoriaListarUseCase;
import microservice.micronoticias.application.core.usecase.EditoriaUpdateUseCase;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategyToCreateEditor;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategyToUpdateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EditoriaUseCaseConfig {

    @Bean
    public EditoriaCriarUseCase editoriaCriarUseCase(EditoriaSalvarAdapter editoriaSalvarAdapter,
                                 EditoriaBuscarPorNomenclaturaAdapter editoriaBuscarPorNomenclaturaAdapter,
                                                     List<RuleStrategyToCreateEditor> ruleStrategies) {
        return new EditoriaCriarUseCase(editoriaSalvarAdapter, editoriaBuscarPorNomenclaturaAdapter, ruleStrategies);
    }

    @Bean
    public EditoriaUpdateUseCase editoriaUpdateUseCase(EditoriaUpdateAdapter editoriaUpdateAdapter,
                                                       List<RuleStrategyToUpdateEditor> ruleStrategies) {
        return new EditoriaUpdateUseCase(editoriaUpdateAdapter, ruleStrategies);
    }

    @Bean
    public EditoriaListarUseCase editoriaListarUseCase(EditoriaListarAdapter editoriaListarAdapter) {
        return new EditoriaListarUseCase(editoriaListarAdapter);
    }

    @Bean
    public EditoriaDeletarPorIdUseCase editoriaDeletarPorIdUseCase(EditoriaDeletarPorIdAdapter editoriaDeletarPorIdAdapter) {
        return new EditoriaDeletarPorIdUseCase(editoriaDeletarPorIdAdapter);
    }
}

