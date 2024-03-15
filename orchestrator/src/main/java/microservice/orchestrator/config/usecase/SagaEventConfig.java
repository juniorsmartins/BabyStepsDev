package microservice.orchestrator.config.usecase;

import microservice.orchestrator.adapter.out.producer.CarteiroNotifyTopicProducer;
import microservice.orchestrator.application.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaEventConfig {

    @Bean
    public SagaEventStartUseCase sagaEventStartUseCase(
            SagaExecutionControlImpl sagaExecutionControlImpl,
            CarteiroNotifyTopicProducer carteiroNotifyTopicProducer) {
        return new SagaEventStartUseCase(
            sagaExecutionControlImpl,
            carteiroNotifyTopicProducer);
    }

    @Bean
    public SagaEventContinueUseCase sagaEventContinueUseCase(
            SagaExecutionControlImpl sagaExecutionControlImpl,
            CarteiroNotifyTopicProducer carteiroNotifyTopicProducer) {
        return new SagaEventContinueUseCase(
            sagaExecutionControlImpl,
            carteiroNotifyTopicProducer);
    }

    @Bean
    public SagaEventFinishSuccessUseCase sagaEventFinishSuccessUseCase(
            CarteiroNotifyTopicProducer carteiroNotifyTopicProducer) {
        return new SagaEventFinishSuccessUseCase(
            carteiroNotifyTopicProducer);
    }

    @Bean
    public SagaEventFinishFailUseCase sagaEventFinishFailUseCase(
            CarteiroNotifyTopicProducer carteiroNotifyTopicProducer) {
        return new SagaEventFinishFailUseCase(
            carteiroNotifyTopicProducer);
    }

    @Bean
    public SagaExecutionControlImpl sagaExecutionControlImpl() {
        return new SagaExecutionControlImpl();
    }

}

