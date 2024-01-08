package microservice.micronoticias.config.bean;

import microservice.micronoticias.adapter.NoticiaSalvarAdapter;
import microservice.micronoticias.application.core.usecase.NoticiaCriarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoticiaUseCaseConfig {

    @Bean
    public NoticiaCriarUseCase noticiaCadastrarUseCase(NoticiaSalvarAdapter noticiaSalvarAdapter) {
        return new NoticiaCriarUseCase(noticiaSalvarAdapter);
    }
}

