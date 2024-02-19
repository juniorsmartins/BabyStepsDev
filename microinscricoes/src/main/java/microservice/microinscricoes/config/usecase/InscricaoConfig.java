package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.*;
import microservice.microinscricoes.application.core.usecase.InscricaoDeleteUseCase;
import microservice.microinscricoes.application.core.usecase.InscricaoOpenUseCase;
import microservice.microinscricoes.application.core.usecase.InscricaoPesquisarUseCase;
import microservice.microinscricoes.application.core.usecase.InscritoRegisterUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscricaoConfig {

    @Bean
    public InscricaoOpenUseCase inscricaoOpenUseCase(InscricaoSaveAdapter inscricaoSaveAdapter,
                                                     TorneioFindByIdAdapter torneioFindByIdAdapter) {
        return new InscricaoOpenUseCase(inscricaoSaveAdapter, torneioFindByIdAdapter);
    }

    @Bean
    public InscricaoPesquisarUseCase inscricaoPesquisarUseCase(InscricaoPesquisarAdapter inscricaoPesquisarAdapter) {
        return new InscricaoPesquisarUseCase(inscricaoPesquisarAdapter);
    }

    @Bean
    public InscricaoDeleteUseCase inscricaoDeleteUseCase(InscricaoDeleteAdapter inscricaoDeleteAdapter) {
        return new InscricaoDeleteUseCase(inscricaoDeleteAdapter);
    }

    @Bean
    public InscritoRegisterUseCase inscritoRegisterUseCase(InscritoSaveAdapter inscritoSaveAdapter) {
        return new InscritoRegisterUseCase(inscritoSaveAdapter);
    }
}

