package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.InscricaoFindByIdAdapter;
import microservice.microinscricoes.adapter.out.InscritoSaveAdapter;
import microservice.microinscricoes.adapter.out.TimeFindByIdAdapter;
import microservice.microinscricoes.application.core.usecase.InscritoRegisterUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscritoConfig {

    @Bean
    public InscritoRegisterUseCase inscritoRegisterUseCase(InscritoSaveAdapter inscritoSaveAdapter,
                                                           InscricaoFindByIdAdapter inscricaoFindByIdAdapter,
                                                           TimeFindByIdAdapter timeFindByIdAdapter) {
        return new InscritoRegisterUseCase(inscritoSaveAdapter, inscricaoFindByIdAdapter, timeFindByIdAdapter);
    }
}

