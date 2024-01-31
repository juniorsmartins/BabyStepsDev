package microservice.microtorneios.config.usecase;

import microservice.microtorneios.adapters.out.TimeInventorySaveAdapter;
import microservice.microtorneios.application.core.usecase.TimeInventoryCreateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeInventoryConfig {

    @Bean
    public TimeInventoryCreateUseCase timeInventoryCreateUseCase(TimeInventorySaveAdapter timeInventorySaveAdapter) {
        return new TimeInventoryCreateUseCase(timeInventorySaveAdapter);
    }
}

