package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.core.domain.enums.EInscricaoStatus;
import microservice.microinscricoes.application.port.input.InscricaoCreateInputPort;
import microservice.microinscricoes.application.port.output.InscricaoSaveOutputPort;
import microservice.microinscricoes.application.port.output.TorneioFindByIdOutputPort;
import microservice.microinscricoes.config.exception.http_404.TorneioNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class InscricaoCreateUseCase implements InscricaoCreateInputPort {

    private static final Logger log = LoggerFactory.getLogger(InscricaoCreateUseCase.class);

    private final InscricaoSaveOutputPort inscricaoSaveOutputPort;

    private final TorneioFindByIdOutputPort torneioFindByIdOutputPort;

    public InscricaoCreateUseCase(InscricaoSaveOutputPort inscricaoSaveOutputPort,
                                  TorneioFindByIdOutputPort torneioFindByIdOutputPort) {
        this.inscricaoSaveOutputPort = inscricaoSaveOutputPort;
        this.torneioFindByIdOutputPort = torneioFindByIdOutputPort;
    }

    @Override
    public Inscricao create(Inscricao inscricao) {

        log.info("Iniciado serviço para criar período de inscrições.");

        var inscricaoOpen = Optional.ofNullable(inscricao)
            .map(this::checkTournamentId)
            .map(this::assignDefaultStatus)
            .map(this.inscricaoSaveOutputPort::save)
            .orElseThrow();

        log.info("Finalizado serviço para criar período de inscrições, com Id: {}.", inscricaoOpen.getId());

        return inscricaoOpen;
    }

    private Inscricao checkTournamentId(Inscricao inscricao) {
        var torneioId = inscricao.getTorneio().getId();

        var torneio = this.torneioFindByIdOutputPort.findById(torneioId)
            .orElseThrow(() -> new TorneioNotFoundException(torneioId));

        inscricao.setTorneio(torneio);

        return inscricao;
    }

    private Inscricao assignDefaultStatus(Inscricao inscricao) {
        inscricao.setStatus(EInscricaoStatus.ATIVO);
        return inscricao;
    }
}

