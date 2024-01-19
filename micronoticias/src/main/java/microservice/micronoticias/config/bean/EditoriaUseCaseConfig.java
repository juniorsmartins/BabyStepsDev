package microservice.micronoticias.config.bean;

import microservice.micronoticias.adapter.out.EditoriaBuscarPorNomenclaturaAdapter;
import microservice.micronoticias.adapter.out.EditoriaDeletarPorIdAdapter;
import microservice.micronoticias.adapter.out.EditoriaListarAdapter;
import microservice.micronoticias.adapter.out.EditoriaSalvarAdapter;
import microservice.micronoticias.application.core.usecase.EditoriaCriarUseCase;
import microservice.micronoticias.application.core.usecase.EditoriaDeletarPorIdUseCase;
import microservice.micronoticias.application.core.usecase.EditoriaListarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EditoriaUseCaseConfig {

    @Bean
    public EditoriaCriarUseCase editoriaCriarUseCase(EditoriaSalvarAdapter editoriaSalvarAdapter,
                                         EditoriaBuscarPorNomenclaturaAdapter editoriaBuscarPorNomenclaturaAdapter) {
        return new EditoriaCriarUseCase(editoriaSalvarAdapter, editoriaBuscarPorNomenclaturaAdapter);
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

