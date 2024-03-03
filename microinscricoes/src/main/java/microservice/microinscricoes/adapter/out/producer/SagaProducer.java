package microservice.microinscricoes.adapter.out.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.mapper.MapperOut;
import microservice.microinscricoes.adapter.utils.JsonUtil;
import microservice.microinscricoes.application.core.domain.SagaEvent;
import microservice.microinscricoes.application.port.output.StartSagaProducerOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SagaProducer implements StartSagaProducerOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final MapperOut mapperOut;

    private final JsonUtil jsonUtil;

    @Value("${spring.kafka.topic.start-saga}")
    private String startSagaTopic;

    public String send(String payload) {
        try {
            log.info("Enviar evento para tópico {} com os dados {}", startSagaTopic, payload);
            kafkaTemplate.send(startSagaTopic, payload);

        } catch (Exception ex) {
            log.error("Erro ao tentar enviar dados para o tópico {} com os dados {}", startSagaTopic, payload, ex);
        }
        return payload;
    }

    @Override
    public void sendEvent(SagaEvent sagaEvent) {

        Optional.ofNullable(sagaEvent)
            .map(this.mapperOut::toSagaEventRequest)
            .map(this.jsonUtil::toJson)
            .map(this::send)
            .orElseThrow();
    }
}

