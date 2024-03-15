package microservice.microtimes.config.usecase;

import microservice.microtimes.adapter.mapper.MapperIn;
import microservice.microtimes.adapter.out.TimeFindAdapter;
import microservice.microtimes.adapter.out.TimeSaveAdapter;
import microservice.microtimes.adapter.out.producer.CarteiroNotifyOrchestratorProducer;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.core.usecase.SagaEventFailUseCase;
import microservice.microtimes.application.core.usecase.SagaEventSuccessUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaEventConfig {

    @Bean
    public SagaEventSuccessUseCase sagaEventSuccessUseCase(
            TimeFindAdapter timeFindAdapter,
            TimeSaveAdapter timeSaveAdapter,
            CarteiroNotifyOrchestratorProducer kafkaProducerOrchestrator,
            MapperIn mapperIn,
            JsonUtil jsonUtil) {
        return new SagaEventSuccessUseCase(
                timeFindAdapter, timeSaveAdapter, kafkaProducerOrchestrator, mapperIn, jsonUtil);
    }

    @Bean
    public SagaEventFailUseCase sagaEventFailUseCase(
            TimeFindAdapter timeFindAdapter,
            TimeSaveAdapter timeSaveAdapter,
            CarteiroNotifyOrchestratorProducer carteiroNotifyOrchestratorProducer,
            MapperIn mapperIn,
            JsonUtil jsonUtil) {
        return new SagaEventFailUseCase(
                timeFindAdapter, timeSaveAdapter, carteiroNotifyOrchestratorProducer, mapperIn, jsonUtil);
    }
}

