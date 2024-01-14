package microservice.micronoticias.config.bean;

import microservice.micronoticias.adapter.NoticiaSalvarAdapter;
import microservice.micronoticias.application.core.usecase.NoticiaCriarUseCase;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategyToCreateNews;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class NoticiaUseCaseConfig {

    @Bean
    public NoticiaCriarUseCase noticiaCadastrarUseCase(NoticiaSalvarAdapter noticiaSalvarAdapter, List<RuleStrategyToCreateNews> ruleStrategies) {
        return new NoticiaCriarUseCase(noticiaSalvarAdapter, ruleStrategies);
    }
}

