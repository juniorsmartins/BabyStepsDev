package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.InscricaoSaveAdapter;
import microservice.microinscricoes.application.core.usecase.InscricaoOpenUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscricaoUseCaseConfig {

    @Bean
    public InscricaoOpenUseCase inscricaoOpenUseCase(InscricaoSaveAdapter inscricaoSaveAdapter) {
        return new InscricaoOpenUseCase(inscricaoSaveAdapter);
    }
}

