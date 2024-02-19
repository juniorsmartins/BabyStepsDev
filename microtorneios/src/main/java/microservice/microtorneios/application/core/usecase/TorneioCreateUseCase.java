package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.adapters.in.dto.response.TorneioSaveDto;
import microservice.microtorneios.application.core.domain.Torneio;
import microservice.microtorneios.application.core.domain.kafka.EncapsulateEvent;
import microservice.microtorneios.application.port.input.TorneioCreateInputPort;
import microservice.microtorneios.application.port.output.NotifyCreationOfNewTorneioOutputPort;
import microservice.microtorneios.application.port.output.TorneioSaveOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TorneioCreateUseCase implements TorneioCreateInputPort {

    private static final Logger log = LoggerFactory.getLogger(TorneioCreateUseCase.class);

    private final TorneioSaveOutputPort torneioSaveOutputPort;

    private final NotifyCreationOfNewTorneioOutputPort notifyCreationOfNewTorneioOutputPort;

    private final EncapsulateEvent encapsulateEvent;

    public TorneioCreateUseCase(TorneioSaveOutputPort torneioSaveOutputPort,
                                NotifyCreationOfNewTorneioOutputPort notifyCreationOfNewTorneioOutputPort,
                                EncapsulateEvent encapsulateEvent) {
        this.torneioSaveOutputPort = torneioSaveOutputPort;
        this.notifyCreationOfNewTorneioOutputPort = notifyCreationOfNewTorneioOutputPort;
        this.encapsulateEvent = encapsulateEvent;
    }

    @Override
    public Torneio create(Torneio torneio) {

        log.info("Iniciado serviço para cadastrar novo Torneio.");

        var torneioSaved = Optional.ofNullable(torneio)
            .map(this.torneioSaveOutputPort::save)
            .map(this::notifyCreationOfNewTorneio)
            .orElseThrow();

        log.info("Finalizado serviço para cadastrar novo torneio, com nome: {}.", torneioSaved.getNome());

        return torneioSaved;
    }

    private Torneio notifyCreationOfNewTorneio(Torneio torneio) {

        Optional.ofNullable(torneio)
            .map(tournament -> new TorneioSaveDto(tournament.getId(), tournament.getNome(), tournament.getAno()))
            .map(this.encapsulateEvent::toEventCreate)
            .map(tournament -> {
                this.notifyCreationOfNewTorneioOutputPort.sendEvent(tournament);
                return true;
            })
            .orElseThrow();

        return torneio;
    }
}

