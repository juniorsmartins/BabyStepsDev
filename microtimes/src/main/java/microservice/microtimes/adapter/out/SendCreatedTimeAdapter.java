package microservice.microtimes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.out.message.TimeMessage;
import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.core.domain.enums.TimeEventEnum;
import microservice.microtimes.application.port.output.SendCreatedTimeOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendCreatedTimeAdapter implements SendCreatedTimeOutputPort {

    @Value("${topic.name}")
    public String topico;

    private final KafkaTemplate<String, TimeMessage> kafkaTemplate;

    @Override
    public void send(Time time, TimeEventEnum event) {

        log.info("Iniciado envio de mensagem via Kafka. Devido ao cadastro do Time, com nome fantasia: {}.", time.getNomeFantasia());

        var timeMessage = TimeMessage.builder()
            .time(time)
            .event(event)
            .build();

        this.kafkaTemplate.send(this.topico, timeMessage);

        log.info("Finalizado envio de mensagem via Kafka. Devido ao cadastro do Time, com nome fantasia: {}.", time.getNomeFantasia());
    }
}

