package microservice.orchestrator.adapter.out.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.orchestrator.adapter.mapper.MapperOut;
import microservice.orchestrator.adapter.utils.JsonUtil;
import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.port.output.CarteiroNotifyTopicOutputPort;
import microservice.orchestrator.config.exception.http_500.CarteiroFailSendLetterException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class CarteiroNotifyTopicProducer implements CarteiroNotifyTopicOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final MapperOut mapperOut;

    private final JsonUtil jsonUtil;

    @Override
    public void sendEvent(String topic, SagaEvent sagaEvent) {

        Optional.ofNullable(sagaEvent)
            .map(this.mapperOut::toSagaEventRequest)
            .map(this.jsonUtil::toJson)
            .ifPresentOrElse(payload -> this.dispatcher(topic, payload),
                () -> {throw new CarteiroFailSendLetterException();}
            );
    }

    private void dispatcher(String topic, String payload) {
        try {
            log.info("Carteiro despacha para o tópico {}, com o conteúdo {}", topic, payload);
            kafkaTemplate.send(topic, payload);

        } catch (Exception ex) {
            log.error("Carteiro falha na tentativa de despachar para o tópico {}, com o conteúdo {}", topic, payload, ex);
        }
    }
}

