package microservice.microtorneios.config.usecase;

import microservice.microtorneios.adapters.mapper.MapperIn;
import microservice.microtorneios.adapters.out.TorneioFindAdapter;
import microservice.microtorneios.adapters.out.TorneioSaveAdapter;
import microservice.microtorneios.adapters.out.producer.CarteiroNotifyOrchestratorProducer;
import microservice.microtorneios.adapters.utils.JsonUtil;
import microservice.microtorneios.application.core.usecase.SagaEventUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaEventConfig {

    @Bean
    public SagaEventUseCase sagaEventUseCase(MapperIn mapperIn, JsonUtil jsonUtil,
                                                       TorneioFindAdapter torneioFindAdapter,
                                                       TorneioSaveAdapter torneioSaveAdapter,
                                                       CarteiroNotifyOrchestratorProducer carteiroNotifyOrchestratorProducer) {
        return new SagaEventUseCase(mapperIn, jsonUtil, torneioFindAdapter, torneioSaveAdapter,
                carteiroNotifyOrchestratorProducer);
    }
}

