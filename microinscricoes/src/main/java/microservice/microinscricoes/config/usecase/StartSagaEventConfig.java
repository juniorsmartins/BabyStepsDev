package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.OrderSaveAdapter;
import microservice.microinscricoes.adapter.out.SagaEventSaveAdapter;
import microservice.microinscricoes.adapter.out.producer.SagaProducer;
import microservice.microinscricoes.application.core.usecase.StartSagaEventUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartSagaEventConfig {

    @Bean
    public StartSagaEventUseCase startSagaEventUseCase(SagaEventSaveAdapter sagaEventSaveAdapter,
                                                       OrderSaveAdapter orderSaveAdapter,
                                                       SagaProducer sagaProducer) {
        return new StartSagaEventUseCase(sagaEventSaveAdapter, orderSaveAdapter, sagaProducer);
    }
}

