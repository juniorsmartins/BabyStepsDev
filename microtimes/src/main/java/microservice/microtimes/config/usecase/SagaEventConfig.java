package microservice.microtimes.config.usecase;

import microservice.microtimes.adapter.out.SagaEventSaveSuccessValidationAdapter;
import microservice.microtimes.application.core.usecase.SagaEventSuccessValidationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaEventConfig {

    @Bean
    public SagaEventSuccessValidationUseCase sagaEventSuccessValidationUseCase(
            SagaEventSaveSuccessValidationAdapter sagaEventSaveSuccessValidationAdapter) {
        return new SagaEventSuccessValidationUseCase(sagaEventSaveSuccessValidationAdapter);
    }
}

