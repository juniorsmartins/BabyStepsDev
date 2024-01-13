package microservice.micronoticias.config.bean;

import microservice.micronoticias.adapter.out.EditoriaSalvarAdapter;
import microservice.micronoticias.application.core.usecase.EditoriaCriarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EditoriaUseCaseConfig {

    @Bean
    public EditoriaCriarUseCase editoriaCriarUseCase(EditoriaSalvarAdapter editoriaSalvarAdapter) {
        return new EditoriaCriarUseCase(editoriaSalvarAdapter);
    }
}

