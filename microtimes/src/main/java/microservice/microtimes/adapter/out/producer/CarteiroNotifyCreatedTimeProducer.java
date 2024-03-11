package microservice.microtimes.adapter.out.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.utils.EncapsulateEvent;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.port.output.NotifyCreateTimeOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarteiroNotifyCreatedTimeProducer implements NotifyCreateTimeOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final EncapsulateEvent encapsulateEvent;

    private final JsonUtil jsonUtil;

    @Value("${spring.kafka.topic.time-save}")
    private String timeSaveTopic;

    @Override
    public void sendEvent(Time time) {

        Optional.ofNullable(time)
            .map(this.encapsulateEvent::toEventCreateTime)
            .map(this.jsonUtil::toJson)
            .map(payload -> {
                try {
                    log.info("Enviando evento para o tópico {}, com os dados {}", timeSaveTopic, payload);
                    this.kafkaTemplate.send(timeSaveTopic, payload);

                } catch (Exception ex) {
                    log.error("Erro ao tentar enviar dados para o tópico {}, com o payload {}", timeSaveTopic, payload, ex);
                }
                return payload;
            })
            .orElseThrow();
    }
}

