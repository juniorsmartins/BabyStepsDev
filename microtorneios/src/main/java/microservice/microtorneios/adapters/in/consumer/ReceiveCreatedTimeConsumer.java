package microservice.microtorneios.adapters.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.out.message.TimeMessage;
import microservice.microtorneios.application.core.domain.enums.TimeEventEnum;
import microservice.microtorneios.application.port.input.TimeInventoryCreateInputPort;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReceiveCreatedTimeConsumer {

    private final TimeInventoryCreateInputPort timeInventoryCreateInputPort;

    @Value("${spring.kafka.group-id}")
    private String groupId;

    @Value("${topic.name}")
    private String topic;

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void receive(ConsumerRecord<String, TimeMessage> record) {

        if (TimeEventEnum.CREATED_TIME.equals(record.value().getEvent())) {
            log.info("Início do registro de Time criado.");

            this.timeInventoryCreateInputPort.create(record.value().getTime());

            log.info("Registro bem-sucedido de Time, com nomeFantasia: {}", record.value().getTime().getNomeFantasia());
        }
    }
}

