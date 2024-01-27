package microservice.microtimes.config.usecase;

import microservice.microtimes.adapter.out.SendCreatedTimeAdapter;
import microservice.microtimes.adapter.out.TimeSaveAdapter;
import microservice.microtimes.application.core.usecase.TimeCreateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeUseCaseConfig {

    @Bean
    public TimeCreateUseCase timeCreateUseCase(TimeSaveAdapter timeSaveAdapter,
                                               SendCreatedTimeAdapter sendCreatedTimeAdapter) {
        return new TimeCreateUseCase(timeSaveAdapter, sendCreatedTimeAdapter);
    }
}

