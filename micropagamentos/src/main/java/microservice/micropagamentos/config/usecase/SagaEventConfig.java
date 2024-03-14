package microservice.micropagamentos.config.usecase;

import microservice.micropagamentos.adapter.mapper.MapperIn;
import microservice.micropagamentos.adapter.out.*;
import microservice.micropagamentos.adapter.out.producer.CarteiroNotifyOrchestratorProducer;
import microservice.micropagamentos.adapter.utils.JsonUtil;
import microservice.micropagamentos.application.core.usecase.SagaEventFailUseCase;
import microservice.micropagamentos.application.core.usecase.SagaEventSuccessUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaEventConfig {

    @Bean
    public SagaEventSuccessUseCase sagaEventSuccessUseCase(
            PagamentoExistsAdapter pagamentoExistsAdapter,
            PagamentoSaveAdapter pagamentoSaveAdapter,
            PagamentoFindAdapter pagamentoFindAdapter,
            CarteiroNotifyOrchestratorProducer carteiroNotifyOrchestratorProducer,
            MapperIn mapperIn,
            JsonUtil jsonUtil
    ) {
        return new SagaEventSuccessUseCase(pagamentoExistsAdapter, pagamentoSaveAdapter, pagamentoFindAdapter,
                carteiroNotifyOrchestratorProducer, mapperIn, jsonUtil);
    }

    @Bean
    public SagaEventFailUseCase sagaEventFailUseCase(
            PagamentoFindAdapter pagamentoFindAdapter,
            PagamentoDeleteAdapter pagamentoDeleteAdapter,
            CarteiroNotifyOrchestratorProducer carteiroNotifyOrchestratorProducer,
            MapperIn mapperIn,
            JsonUtil jsonUtil
    ) {
        return new SagaEventFailUseCase(pagamentoFindAdapter, pagamentoDeleteAdapter, carteiroNotifyOrchestratorProducer,
                mapperIn, jsonUtil);
    }

}

