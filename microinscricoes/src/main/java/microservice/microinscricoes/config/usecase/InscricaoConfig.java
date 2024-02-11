package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.FindByTorneioIdAdapter;
import microservice.microinscricoes.adapter.out.InscricaoSaveAdapter;
import microservice.microinscricoes.adapter.out.InscritoSaveAdapter;
import microservice.microinscricoes.application.core.usecase.InscricaoOpenUseCase;
import microservice.microinscricoes.application.core.usecase.InscritoRegisterUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscricaoConfig {

    @Bean
    public InscricaoOpenUseCase inscricaoOpenUseCase(InscricaoSaveAdapter inscricaoSaveAdapter,
                                                     FindByTorneioIdAdapter findByTorneioIdAdapter) {
        return new InscricaoOpenUseCase(inscricaoSaveAdapter, findByTorneioIdAdapter);
    }

    @Bean
    public InscritoRegisterUseCase inscritoRegisterUseCase(InscritoSaveAdapter inscritoSaveAdapter) {
        return new InscritoRegisterUseCase(inscritoSaveAdapter);
    }
}

