package microservice.microtorneios.config.usecase;

import microservice.microtorneios.adapters.out.TimeSaveAdapter;
import microservice.microtorneios.application.core.usecase.TimeCreateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeInventoryConfig {

    @Bean
    public TimeCreateUseCase timeInventoryCreateUseCase(TimeSaveAdapter timeInventorySaveAdapter) {
        return new TimeCreateUseCase(timeInventorySaveAdapter);
    }
}

