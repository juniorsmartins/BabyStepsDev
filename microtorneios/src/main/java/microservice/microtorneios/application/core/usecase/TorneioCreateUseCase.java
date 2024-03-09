package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.application.core.domain.Torneio;
import microservice.microtorneios.application.port.input.TorneioCreateInputPort;
import microservice.microtorneios.application.port.output.NotifyCreationOfNewTorneioOutputPort;
import microservice.microtorneios.application.port.output.TorneioSaveOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.Optional;

public class TorneioCreateUseCase implements TorneioCreateInputPort {

    private static final Logger log = LoggerFactory.getLogger(TorneioCreateUseCase.class);

    private final TorneioSaveOutputPort torneioSaveOutputPort;

    private final NotifyCreationOfNewTorneioOutputPort notifyCreationOfNewTorneioOutputPort;

    public TorneioCreateUseCase(TorneioSaveOutputPort torneioSaveOutputPort,
                                NotifyCreationOfNewTorneioOutputPort notifyCreationOfNewTorneioOutputPort) {
        this.torneioSaveOutputPort = torneioSaveOutputPort;
        this.notifyCreationOfNewTorneioOutputPort = notifyCreationOfNewTorneioOutputPort;
    }

    @Override
    public Torneio create(Torneio torneio) {

        log.info("Iniciado serviço para criar novo Torneio.");

        var torneioSaved = Optional.ofNullable(torneio)
            .map(this.torneioSaveOutputPort::save)
            .map(this::notifyCreationOfNewTorneio)
            .orElseThrow();

        log.info("Finalizado serviço para criar novo torneio: {}.", torneioSaved);

        return torneioSaved;
    }

    private Torneio notifyCreationOfNewTorneio(Torneio torneio) {

        Optional.ofNullable(torneio)
            .ifPresentOrElse(this.notifyCreationOfNewTorneioOutputPort::sendEvent,
                () -> {throw new NoSuchElementException();}
            );

        return torneio;
    }
}

