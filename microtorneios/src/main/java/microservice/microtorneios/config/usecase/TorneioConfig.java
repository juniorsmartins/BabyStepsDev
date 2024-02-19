package microservice.microtorneios.config.usecase;

import microservice.microtorneios.adapters.out.TorneioSaveAdapter;
import microservice.microtorneios.adapters.out.producer.NotifyCreationOfNewTorneioKafkaProducer;
import microservice.microtorneios.application.core.usecase.TorneioCreateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TorneioConfig {

    @Bean
    public TorneioCreateUseCase torneioCreateUseCase(TorneioSaveAdapter torneioSaveAdapter,
                                 NotifyCreationOfNewTorneioKafkaProducer notifyCreationOfNewTorneioKafkaProducer) {
        return new TorneioCreateUseCase(torneioSaveAdapter, notifyCreationOfNewTorneioKafkaProducer);
    }
}

