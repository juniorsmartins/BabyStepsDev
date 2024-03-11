package microservice.microinscricoes.adapter.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTime;
import microservice.microinscricoes.adapter.mapper.MapperIn;
import microservice.microinscricoes.adapter.utils.JsonUtil;
import microservice.microinscricoes.application.port.input.TimeCreateInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CaixaPostalTimeConsumer {

    private final JsonUtil jsonUtil;

    private final MapperIn mapper;

    private final TimeCreateInputPort timeSaveInputPort;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.time-save}"
    )
    public void consumeEventCreateTime(String payload) {

        log.info("Iniciada mensageria, via tópico time-save, para salvar Time.");

        var response = Optional.ofNullable(payload)
            .map(this.jsonUtil::toEventCreateTime)
            .map(EventCreateTime::time)
            .map(this.mapper::toTime)
            .map(this.timeSaveInputPort::save)
            .orElseThrow();

        log.info("Finalizada mensageria, via tópico time-save, para salvar Time {}.", response);
    }

}

