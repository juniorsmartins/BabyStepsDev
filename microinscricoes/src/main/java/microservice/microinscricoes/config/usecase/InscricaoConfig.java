package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.InscricaoPesquisarAdapter;
import microservice.microinscricoes.adapter.out.TorneioFindByIdAdapter;
import microservice.microinscricoes.adapter.out.InscricaoSaveAdapter;
import microservice.microinscricoes.adapter.out.InscritoSaveAdapter;
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
    public InscritoRegisterUseCase inscritoRegisterUseCase(InscritoSaveAdapter inscritoSaveAdapter) {
        return new InscritoRegisterUseCase(inscritoSaveAdapter);
    }
}

