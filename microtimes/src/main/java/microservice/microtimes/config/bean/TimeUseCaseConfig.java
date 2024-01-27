package microservice.microtimes.config.bean;

import microservice.microtimes.adapter.out.TimeSaveAdapter;
import microservice.microtimes.application.core.usecase.TimeCreateUseCase;
import microservice.microtimes.application.port.output.SendCreatedTimeOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeUseCaseConfig {

    @Bean
    public TimeCreateUseCase timeCreateUseCase(TimeSaveAdapter timeSaveAdapter,
                                               SendCreatedTimeOutputPort sendCreatedTimeOutputPort) {
        return new TimeCreateUseCase(timeSaveAdapter, sendCreatedTimeOutputPort);
    }
}

