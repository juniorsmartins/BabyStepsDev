package microservice.microtorneios.config.usecase;

import microservice.microtorneios.adapters.out.TimeSaveAdapter;
import microservice.microtorneios.application.core.usecase.TimeCreateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeConfig {

    @Bean
    public TimeCreateUseCase timeCreateUseCase(TimeSaveAdapter timeSaveAdapter) {
        return new TimeCreateUseCase(timeSaveAdapter);
    }
}

