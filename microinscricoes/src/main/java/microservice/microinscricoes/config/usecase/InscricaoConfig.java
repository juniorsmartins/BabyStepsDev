package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.InscricaoDeleteAdapter;
import microservice.microinscricoes.adapter.out.InscricaoPesquisarAdapter;
import microservice.microinscricoes.adapter.out.InscricaoSaveAdapter;
import microservice.microinscricoes.adapter.out.TorneioFindByIdAdapter;
import microservice.microinscricoes.application.core.usecase.InscricaoDeleteUseCase;
import microservice.microinscricoes.application.core.usecase.InscricaoCreateUseCase;
import microservice.microinscricoes.application.core.usecase.InscricaoPesquisarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscricaoConfig {

    @Bean
    public InscricaoCreateUseCase inscricaoCreateUseCase(InscricaoSaveAdapter inscricaoSaveAdapter,
                                                       TorneioFindByIdAdapter torneioFindByIdAdapter) {
        return new InscricaoCreateUseCase(inscricaoSaveAdapter, torneioFindByIdAdapter);
    }

    @Bean
    public InscricaoPesquisarUseCase inscricaoPesquisarUseCase(InscricaoPesquisarAdapter inscricaoPesquisarAdapter) {
        return new InscricaoPesquisarUseCase(inscricaoPesquisarAdapter);
    }

    @Bean
    public InscricaoDeleteUseCase inscricaoDeleteUseCase(InscricaoDeleteAdapter inscricaoDeleteAdapter) {
        return new InscricaoDeleteUseCase(inscricaoDeleteAdapter);
    }
}

