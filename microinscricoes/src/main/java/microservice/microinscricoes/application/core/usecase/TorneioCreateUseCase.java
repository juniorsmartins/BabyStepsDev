package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.Torneio;
import microservice.microinscricoes.application.port.input.TorneioCreateInputPort;
import microservice.microinscricoes.application.port.output.TorneioSaveOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TorneioCreateUseCase implements TorneioCreateInputPort {

    private static final Logger log = LoggerFactory.getLogger(TorneioCreateUseCase.class);

    private final TorneioSaveOutputPort torneioSaveOutputPort;

    public TorneioCreateUseCase(TorneioSaveOutputPort torneioSaveOutputPort) {
        this.torneioSaveOutputPort = torneioSaveOutputPort;
    }

    @Override
    public Torneio save(final Torneio torneio) {

        log.info("Iniciado serviço para criar Torneio.");

        var torneioRegister = Optional.ofNullable(torneio)
            .map(this.torneioSaveOutputPort::save)
            .orElseThrow();

        log.info("Finalizado serviço para criar Torneio {}.", torneioRegister);

        return torneioRegister;
    }
}

