package microservice.microtorneios.config.usecase;

import microservice.microtorneios.adapters.mapper.MapperIn;
import microservice.microtorneios.adapters.utils.JsonUtil;
import microservice.microtorneios.application.core.usecase.SagaEventValidationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaEventConfig {

    @Bean
    public SagaEventValidationUseCase sagaEventValidationUseCase(MapperIn mapperIn, JsonUtil jsonUtil) {
        return new SagaEventValidationUseCase(mapperIn, jsonUtil);
    }
}

