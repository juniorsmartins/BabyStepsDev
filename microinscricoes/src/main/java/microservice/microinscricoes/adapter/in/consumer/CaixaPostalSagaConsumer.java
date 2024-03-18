package microservice.microinscricoes.adapter.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.mapper.MapperIn;
import microservice.microinscricoes.adapter.utils.JsonUtil;
import microservice.microinscricoes.application.port.output.SagaEventSaveOutputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CaixaPostalSagaConsumer {

    private final JsonUtil jsonUtil;

    private final MapperIn mapper;

    private final SagaEventSaveOutputPort sagaEventSaveOutputPort;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.notify-ending}"
    )
    public void consumeNotifyEndingEvent(String payload) {

        log.info("Recebido evento de conclusão da saga, via tópico Notify-Ending, no ConsumerEvent.");

        var sagaEvent = Optional.ofNullable(payload)
            .map(this.jsonUtil::toSagaEventRequest)
            .map(this.mapper::toSagaEvent)
            .map(this.sagaEventSaveOutputPort::save)
            .orElseThrow();

        log.info("Saga concluída com sucesso, via tópico Notify-Ending, com o conteúdo: {}", sagaEvent);
    }

}

