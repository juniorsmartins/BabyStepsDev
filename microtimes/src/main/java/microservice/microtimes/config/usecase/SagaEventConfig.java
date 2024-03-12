package microservice.microtimes.config.usecase;

import microservice.microtimes.adapter.mapper.MapperIn;
import microservice.microtimes.adapter.out.SagaEventExistsAdapter;
import microservice.microtimes.adapter.out.SagaEventFindAdapter;
import microservice.microtimes.adapter.out.SagaEventSaveAdapter;
import microservice.microtimes.adapter.out.producer.CarteiroNotifyOrchestratorProducer;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.core.usecase.SagaEventUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaEventConfig {

    @Bean
    public SagaEventUseCase sagaEventSuccessValidationUseCase(
            SagaEventExistsAdapter sagaEventExistsAdapter,
            SagaEventSaveAdapter sagaEventSaveSuccessValidationAdapter,
            CarteiroNotifyOrchestratorProducer kafkaProducerOrchestrator,
            SagaEventFindAdapter sagaEventFindAdapter,
            MapperIn mapperIn,
            JsonUtil jsonUtil) {
        return new SagaEventUseCase(sagaEventExistsAdapter,
                sagaEventSaveSuccessValidationAdapter,
                kafkaProducerOrchestrator,
                sagaEventFindAdapter,
                mapperIn,
                jsonUtil);
    }
}

