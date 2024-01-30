package microservice.microtorneios.adapters.in.consumer;

import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.out.message.TimeMessage;
import microservice.microtorneios.application.core.domain.enums.TimeEventEnum;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReceiveCreatedTimeConsumer {

    @KafkaListener(topics = {"tp-saga-time"}, groupId = "time-inventory")
    public void receive(TimeMessage timeMessage) {

        if (TimeEventEnum.CREATED_TIME.equals(timeMessage.getEvent())) {
            log.info("Início do registro de Time criado.");

            log.info("Registro bem-sucedido de Time, com nomeFantasia: {}", timeMessage.getTime().getNomeFantasia());
        }
    }
}

