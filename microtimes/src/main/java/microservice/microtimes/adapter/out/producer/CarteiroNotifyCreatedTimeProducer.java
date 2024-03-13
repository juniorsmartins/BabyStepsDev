package microservice.microtimes.adapter.out.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.in.controller.dto.TimeIdDto;
import microservice.microtimes.adapter.out.producer.event.EventCreateTime;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.port.output.CarteiroNotifyCreatedTimeOutputPort;
import microservice.microtimes.config.exception.http_500.CarteiroFailSendLetterException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarteiroNotifyCreatedTimeProducer implements CarteiroNotifyCreatedTimeOutputPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final JsonUtil jsonUtil;

    @Value("${spring.kafka.topic.time-save}")
    private String timeSaveTopic;

    @Override
    public void sendEvent(Time time) {

        Optional.ofNullable(time)
            .map(this::toEventCreateTime)
            .map(this.jsonUtil::toJson)
            .ifPresentOrElse(this::dispatch, () -> {throw new CarteiroFailSendLetterException();});
    }

    private EventCreateTime toEventCreateTime(final Time time) {

        return Optional.ofNullable(time)
            .map(team -> new TimeIdDto(team.getId()))
            .map(EventCreateTime::new)
            .orElseThrow();
    }

    private void dispatch(String payload) {
        try {
            log.info("Carteiro despacha para o tópico {}, com o conteúdo {}", timeSaveTopic, payload);
            this.kafkaTemplate.send(timeSaveTopic, payload);

        } catch (Exception ex) {
            log.error("Carteiro falha na tentativa de despachar para o tópico {}, com o conteúdo {}", timeSaveTopic, payload, ex);
        }
    }

}

