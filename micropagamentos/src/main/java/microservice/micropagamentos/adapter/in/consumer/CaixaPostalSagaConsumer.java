package microservice.micropagamentos.adapter.in.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micropagamentos.adapter.mapper.MapperIn;
import microservice.micropagamentos.adapter.utils.JsonUtil;
import microservice.micropagamentos.application.port.input.SagaEventFailInputPort;
import microservice.micropagamentos.application.port.input.SagaEventSuccessInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class CaixaPostalSagaConsumer {

    private final SagaEventSuccessInputPort sagaEventSuccessInputPort;

    private final SagaEventFailInputPort sagaEventFailInputPort;

    private final JsonUtil jsonUtil;

    private final MapperIn mapperIn;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.pagamento-success}"
    )
    public void consumeSuccessSagaEvent(String payload) {

        log.info("Recebendo evento no tópico Pagamento-Success para efetuar Pagamento de inscrito.");

        var sagaEventSuccess = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventSuccessInputPort::realizePayment)
            .orElseThrow();

        log.info("Finalizado evento no tópico Pagamento-Success para efetuar Pagamento de inscrito: {}.", sagaEventSuccess);
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.pagamento-fail}"
    )
    public void consumeFailSagaEvent(String payload) {

        log.info("Recebido evento no tópico Pagamento-Success para efetuar Pagamento de inscrito.");

        var sagaEventFail = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapperIn::toSagaEvent)
            .map(this.sagaEventFailInputPort::realizeRefund)
            .orElseThrow();

        log.info("Finalizado evento no tópico Pagamento-Success para efetuar Pagamento de inscrito: {}.", sagaEventFail);
    }

}

