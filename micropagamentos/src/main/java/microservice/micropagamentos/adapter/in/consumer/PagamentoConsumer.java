package microservice.micropagamentos.adapter.in.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micropagamentos.adapter.mapper.MapperIn;
import microservice.micropagamentos.adapter.utils.JsonUtil;
import microservice.micropagamentos.application.port.input.SagaEventPagamentoInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class PagamentoConsumer {

    private final JsonUtil jsonUtil;

    private final MapperIn mapperIn;

    private final SagaEventPagamentoInputPort sagaEventPagamentoInputPort;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.pagamento-success}"
    )
    public void consumeSuccessEvent(String payload) {

        log.info("Recebendo evento no tópico de sucesso de Pagamento.");

        var sagaEventSuccess = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventPagamentoInputPort::realizePayment)
            .orElseThrow();

        log.info("Finalizado evento no tópico de sucesso de Pagamento: {}.", sagaEventSuccess);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.pagamento-fail}"
    )
    public void consumeFailEvent(String payload) {

        log.info("Recebendo evento no tópico de falha no pagamento.");

        var sagaEventFail = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventPagamentoInputPort::realizeRefund)
            .orElseThrow();

        log.info("Finalizado evento no tópico de falha de pagamento: {}.", sagaEventFail);
    }
}

