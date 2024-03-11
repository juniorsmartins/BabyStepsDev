package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.application.core.domain.Torneio;
import microservice.microtorneios.application.port.input.TorneioCreateInputPort;
import microservice.microtorneios.application.port.output.CarteiroNotifyCreatedTorneioOutputPort;
import microservice.microtorneios.application.port.output.TorneioSaveOutputPort;
import microservice.microtorneios.config.exception.http_500.NullValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TorneioCreateUseCase implements TorneioCreateInputPort {

    private static final Logger log = LoggerFactory.getLogger(TorneioCreateUseCase.class);

    private final TorneioSaveOutputPort torneioSaveOutputPort;

    private final CarteiroNotifyCreatedTorneioOutputPort notifyCreatedTorneioOutputPort;

    public TorneioCreateUseCase(TorneioSaveOutputPort torneioSaveOutputPort,
                                CarteiroNotifyCreatedTorneioOutputPort notifyCreatedTorneioOutputPort) {
        this.torneioSaveOutputPort = torneioSaveOutputPort;
        this.notifyCreatedTorneioOutputPort = notifyCreatedTorneioOutputPort;
    }

    @Override
    public Torneio create(Torneio torneio) {

        log.info("Serviço iniciado para criar novo Torneio.");

        var torneioSaved = Optional.ofNullable(torneio)
            .map(this.torneioSaveOutputPort::save)
            .map(this::notifyCreationOfNewTorneio)
            .orElseThrow(NullValueException::new);

        log.info("Serviço finalizado para criar novo torneio: {}.", torneioSaved);

        return torneioSaved;
    }

    private Torneio notifyCreationOfNewTorneio(Torneio torneio) {

        Optional.ofNullable(torneio)
            .ifPresentOrElse(this.notifyCreatedTorneioOutputPort::sendEvent,
                () -> {throw new NullValueException();}
            );

        return torneio;
    }
}

