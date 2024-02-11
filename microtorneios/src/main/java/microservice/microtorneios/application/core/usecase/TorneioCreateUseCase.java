package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.adapters.in.utils.JsonUtil;
import microservice.microtorneios.application.core.domain.Torneio;
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

    private final JsonUtil jsonUtil;

    public TorneioCreateUseCase(TorneioSaveOutputPort torneioSaveOutputPort,
                                NotifyCreationOfNewTorneioOutputPort notifyCreationOfNewTorneioOutputPort,
                                JsonUtil jsonUtil) {
        this.torneioSaveOutputPort = torneioSaveOutputPort;
        this.notifyCreationOfNewTorneioOutputPort = notifyCreationOfNewTorneioOutputPort;
        this.jsonUtil = jsonUtil;
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
        var payload = this.jsonUtil.toJson(torneio);
        this.notifyCreationOfNewTorneioOutputPort.sendEvent(payload);
        return torneio;
    }
}

