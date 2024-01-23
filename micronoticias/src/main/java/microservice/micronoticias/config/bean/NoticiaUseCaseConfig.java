package microservice.micronoticias.config.bean;

import microservice.micronoticias.adapter.out.NoticiaDeleteAdapter;
import microservice.micronoticias.adapter.out.NoticiaSalvarAdapter;
import microservice.micronoticias.adapter.out.NoticiaUpdateAdapter;
import microservice.micronoticias.application.core.usecase.NoticiaCriarUseCase;
import microservice.micronoticias.application.core.usecase.NoticiaDeleteUseCase;
import microservice.micronoticias.application.core.usecase.NoticiaUpdateUseCase;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategyToCreateNews;
import microservice.micronoticias.application.core.usecase.regras.RuleStrategyToUpdateNews;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class NoticiaUseCaseConfig {

    @Bean
    public NoticiaCriarUseCase noticiaCadastrarUseCase(NoticiaSalvarAdapter noticiaSalvarAdapter,
                                                       List<RuleStrategyToCreateNews> ruleStrategies) {
        return new NoticiaCriarUseCase(noticiaSalvarAdapter, ruleStrategies);
    }

    @Bean
    public NoticiaUpdateUseCase noticiaUpdateUseCase(NoticiaUpdateAdapter noticiaUpdateAdapter,
                                                     List<RuleStrategyToUpdateNews> ruleStrategies) {
        return new NoticiaUpdateUseCase(noticiaUpdateAdapter, ruleStrategies);
    }

    @Bean
    public NoticiaDeleteUseCase noticiaDeleteUseCase(NoticiaDeleteAdapter noticiaDeleteAdapter) {
        return new NoticiaDeleteUseCase(noticiaDeleteAdapter);
    }
}

