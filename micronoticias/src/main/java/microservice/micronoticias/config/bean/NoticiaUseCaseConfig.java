package microservice.micronoticias.config.bean;

import microservice.micronoticias.adapter.NoticiaSalvarAdapter;
import microservice.micronoticias.application.core.usecase.NoticiaCriarUseCase;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class NoticiaUseCaseConfig {

    @Bean
    public NoticiaCriarUseCase noticiaCadastrarUseCase(NoticiaSalvarAdapter noticiaSalvarAdapter, List<RuleStrategy> ruleStrategies) {
        return new NoticiaCriarUseCase(noticiaSalvarAdapter, ruleStrategies);
    }
}

