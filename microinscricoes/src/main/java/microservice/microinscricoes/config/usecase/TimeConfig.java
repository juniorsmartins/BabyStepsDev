package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.TimeSaveAdapter;
import microservice.microinscricoes.application.core.usecase.TimeSaveUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeConfig {

    @Bean
    public TimeSaveUseCase timeSaveUseCase(TimeSaveAdapter timeSaveAdapter) {
        return new TimeSaveUseCase(timeSaveAdapter);
    }
}

