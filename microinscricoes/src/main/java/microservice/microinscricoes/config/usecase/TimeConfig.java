package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.TimeSaveAdapter;
import microservice.microinscricoes.application.core.usecase.TimeCreateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeConfig {

    @Bean
    public TimeCreateUseCase timeSaveUseCase(TimeSaveAdapter timeSaveAdapter) {
        return new TimeCreateUseCase(timeSaveAdapter);
    }
}

