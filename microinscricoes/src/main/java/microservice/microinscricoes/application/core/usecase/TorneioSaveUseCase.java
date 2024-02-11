package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.Torneio;
import microservice.microinscricoes.application.port.input.TorneioSaveInputPort;
import microservice.microinscricoes.application.port.output.TorneioSaveOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TorneioSaveUseCase implements TorneioSaveInputPort {

    private static final Logger log = LoggerFactory.getLogger(TorneioSaveUseCase.class);

    private final TorneioSaveOutputPort torneioSaveOutputPort;

    public TorneioSaveUseCase(TorneioSaveOutputPort torneioSaveOutputPort) {
        this.torneioSaveOutputPort = torneioSaveOutputPort;
    }

    @Override
    public void save(final Torneio torneio) {

        log.info("Iniciado serviço para salvar Torneio.");

        this.torneioSaveOutputPort.save(torneio);

        log.info("Finalizado serviço para salvar Torneio {}.", torneio);
    }
}

