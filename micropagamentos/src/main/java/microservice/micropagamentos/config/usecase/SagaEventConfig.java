package microservice.micropagamentos.config.usecase;

import microservice.micropagamentos.adapter.mapper.MapperIn;
import microservice.micropagamentos.adapter.out.SagaEventExistsAdapter;
import microservice.micropagamentos.adapter.out.SagaEventFindAdapter;
import microservice.micropagamentos.adapter.out.SagaEventSavePagamentoAdapter;
import microservice.micropagamentos.adapter.out.producer.KafkaProducerOrchestrator;
import microservice.micropagamentos.adapter.utils.JsonUtil;
import microservice.micropagamentos.application.core.usecase.SagaEventPagamentoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaEventConfig {

    @Bean
    public SagaEventPagamentoUseCase sagaEventPagamentoUseCase(SagaEventSavePagamentoAdapter sagaEventSavePagamentoAdapter,
                                                               KafkaProducerOrchestrator kafkaProducerOrchestrator,
                                                               SagaEventExistsAdapter sagaEventExistsAdapter,
                                                               SagaEventFindAdapter sagaEventFindAdapter,
                                                               MapperIn mapperIn,
                                                               JsonUtil jsonUtil) {
        return new SagaEventPagamentoUseCase(sagaEventSavePagamentoAdapter, kafkaProducerOrchestrator,
                sagaEventExistsAdapter, sagaEventFindAdapter, mapperIn, jsonUtil);
    }
}

