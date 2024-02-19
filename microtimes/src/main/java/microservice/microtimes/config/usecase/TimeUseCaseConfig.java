package microservice.microtimes.config.usecase;

import microservice.microtimes.adapter.out.TimeSaveAdapter;
import microservice.microtimes.adapter.out.producer.NotifyCreationOfNewTimeKafkaProducer;
import microservice.microtimes.application.core.usecase.TimeCreateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeUseCaseConfig {

    @Bean
    public TimeCreateUseCase timeCreateUseCase(TimeSaveAdapter timeSaveAdapter,
                                               NotifyCreationOfNewTimeKafkaProducer notifyCreationOfNewTimeKafkaProducer) {
        return new TimeCreateUseCase(timeSaveAdapter, notifyCreationOfNewTimeKafkaProducer);
    }
}

