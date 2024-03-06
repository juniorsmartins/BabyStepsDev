package microservice.microtimes.config.usecase;

import microservice.microtimes.adapter.mapper.MapperIn;
import microservice.microtimes.adapter.out.SagaEventExistsAdapter;
import microservice.microtimes.adapter.out.SagaEventFindAdapter;
import microservice.microtimes.adapter.out.SagaEventSaveValidationAdapter;
import microservice.microtimes.adapter.out.producer.KafkaProducerOrchestrator;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.core.usecase.SagaEventValidationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaEventConfig {

    @Bean
    public SagaEventValidationUseCase sagaEventSuccessValidationUseCase(
            SagaEventExistsAdapter sagaEventExistsAdapter,
            SagaEventSaveValidationAdapter sagaEventSaveSuccessValidationAdapter,
            KafkaProducerOrchestrator kafkaProducerOrchestrator,
            SagaEventFindAdapter sagaEventFindAdapter,
            MapperIn mapperIn,
            JsonUtil jsonUtil) {
        return new SagaEventValidationUseCase(sagaEventExistsAdapter,
                sagaEventSaveSuccessValidationAdapter,
                kafkaProducerOrchestrator,
                sagaEventFindAdapter,
                mapperIn,
                jsonUtil);
    }
}

