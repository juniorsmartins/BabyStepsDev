package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.SagaEventFindAllAdapter;
import microservice.microinscricoes.adapter.out.SagaEventFindByFiltersAdapter;
import microservice.microinscricoes.adapter.out.SagaEventSaveAdapter;
import microservice.microinscricoes.adapter.out.producer.CarteiroNotifyStartProducer;
import microservice.microinscricoes.application.core.usecase.SagaEventFindAllUseCase;
import microservice.microinscricoes.application.core.usecase.SagaEventFindByFiltersUseCase;
import microservice.microinscricoes.application.core.usecase.StartSagaEventUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaEventConfig {

    @Bean
    public StartSagaEventUseCase startSagaEventUseCase(SagaEventSaveAdapter sagaEventSaveAdapter,
                                                       CarteiroNotifyStartProducer sagaProducer) {
        return new StartSagaEventUseCase(sagaEventSaveAdapter, sagaProducer);
    }

    @Bean
    public SagaEventFindAllUseCase sagaEventFindAllUseCase(SagaEventFindAllAdapter sagaEventFindAllAdapter) {
        return new SagaEventFindAllUseCase(sagaEventFindAllAdapter);
    }

    @Bean
    public SagaEventFindByFiltersUseCase sagaEventFindByFiltersUseCase(SagaEventFindByFiltersAdapter sagaEventFindByFiltersAdapter) {
        return new SagaEventFindByFiltersUseCase(sagaEventFindByFiltersAdapter);
    }
}

