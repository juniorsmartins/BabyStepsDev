package microservice.microinscricoes.adapter.in.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.in.consumer.event.EventCreateTorneio;
import microservice.microinscricoes.adapter.mapper.MapperIn;
import microservice.microinscricoes.adapter.utils.JsonUtil;
import microservice.microinscricoes.application.port.input.TorneioCreateInputPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CaixaPostalTorneioConsumer {

    private final JsonUtil jsonUtil;

    private final MapperIn mapper;

    private final TorneioCreateInputPort torneioSaveInputPort;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.torneio-save}"
    )
    public void consumeEventCreateTorneio(String payload) {

        log.info("Iniciada mensageria, via tópico torneio-save, para salvar Torneio.");

        var response = Optional.ofNullable(payload)
            .map(this.jsonUtil::toEventCreateTorneio)
            .map(EventCreateTorneio::torneio)
            .map(this.mapper::toTorneio)
            .map(this.torneioSaveInputPort::save)
            .orElseThrow();

        log.info("Finalizada mensageria, via tópico torneio-save, para salvar Torneio {}.", response);
    }

}

