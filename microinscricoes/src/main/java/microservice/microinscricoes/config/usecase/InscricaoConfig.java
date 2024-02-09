package microservice.microinscricoes.config.usecase;

import microservice.microinscricoes.adapter.out.InscricaoSaveAdapter;
import microservice.microinscricoes.adapter.out.InscritoSaveAdapter;
import microservice.microinscricoes.adapter.out.kafka.producer.KafkaProducerFindIdTorneio;
import microservice.microinscricoes.application.core.usecase.InscricaoOpenUseCase;
import microservice.microinscricoes.application.core.usecase.InscritoRegisterUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscricaoConfig {

    @Bean
    public InscricaoOpenUseCase inscricaoOpenUseCase(InscricaoSaveAdapter inscricaoSaveAdapter,
                                                     KafkaProducerFindIdTorneio kafkaProducerFindIdTorneio) {
        return new InscricaoOpenUseCase(inscricaoSaveAdapter, kafkaProducerFindIdTorneio);
    }

    @Bean
    public InscritoRegisterUseCase inscritoRegisterUseCase(InscritoSaveAdapter inscritoSaveAdapter) {
        return new InscritoRegisterUseCase(inscritoSaveAdapter);
    }
}

