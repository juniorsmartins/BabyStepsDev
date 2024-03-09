package microservice.microtorneios.adapters.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.out.producer.dto.TorneioSaveDto;
import microservice.microtorneios.adapters.out.producer.event.EventCreateTorneio;
import microservice.microtorneios.application.core.domain.Torneio;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class EncapsulateEventImpl implements EncapsulateEvent {

    @Override
    public EventCreateTorneio toEventCreateTorneio(Torneio torneio) {

        return Optional.ofNullable(torneio)
            .map(tournament -> new TorneioSaveDto(tournament.getId(), tournament.getNome(), tournament.getAno()))
            .map(EventCreateTorneio::new)
            .orElseThrow();
    }
}

