package microservice.microtimes.config.usecase;

import microservice.microtimes.adapter.out.TimeSaveAdapter;
import microservice.microtimes.adapter.out.producer.CarteiroNotifyCreatedTimeProducer;
import microservice.microtimes.application.core.usecase.TimeCreateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeConfig {

    @Bean
    public TimeCreateUseCase timeCreateUseCase(TimeSaveAdapter timeSaveAdapter,
                                               CarteiroNotifyCreatedTimeProducer notifyCreationOfNewTimeKafkaProducer) {
        return new TimeCreateUseCase(timeSaveAdapter, notifyCreationOfNewTimeKafkaProducer);
    }
}

