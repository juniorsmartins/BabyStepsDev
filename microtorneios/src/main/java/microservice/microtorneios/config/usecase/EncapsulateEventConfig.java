package microservice.microtorneios.config.usecase;

import microservice.microtorneios.application.core.domain.kafka.EncapsulateEventImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncapsulateEventConfig {

    @Bean
    public EncapsulateEventImpl encapsulateEvent() {
        return new EncapsulateEventImpl();
    }
}

