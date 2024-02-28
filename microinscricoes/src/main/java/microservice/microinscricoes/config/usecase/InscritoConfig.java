package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.InscricaoFindByIdAdapter;
import microservice.microinscricoes.adapter.out.InscritoSaveAdapter;
import microservice.microinscricoes.adapter.out.TimeFindByIdAdapter;
import microservice.microinscricoes.application.core.usecase.InscritoCreateUseCase;
import microservice.microinscricoes.application.core.usecase.StartSagaEventUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscritoConfig {

    @Bean
    public InscritoCreateUseCase inscritoRegisterUseCase(InscritoSaveAdapter inscritoSaveAdapter,
                                                         InscricaoFindByIdAdapter inscricaoFindByIdAdapter,
                                                         TimeFindByIdAdapter timeFindByIdAdapter,
                                                         StartSagaEventUseCase startSagaEventUseCase) {
        return new InscritoCreateUseCase(inscritoSaveAdapter, inscricaoFindByIdAdapter, timeFindByIdAdapter,
                startSagaEventUseCase);
    }
}

