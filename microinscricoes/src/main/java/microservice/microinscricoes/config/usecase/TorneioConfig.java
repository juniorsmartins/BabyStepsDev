package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.TorneioSaveAdapter;
import microservice.microinscricoes.application.core.usecase.TorneioSaveUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TorneioConfig {

    @Bean
    public TorneioSaveUseCase torneioSaveUseCase(TorneioSaveAdapter torneioSaveAdapter) {
        return new TorneioSaveUseCase(torneioSaveAdapter);
    }
}

