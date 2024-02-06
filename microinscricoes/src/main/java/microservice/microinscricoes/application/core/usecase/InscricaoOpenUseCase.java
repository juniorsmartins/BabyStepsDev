package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.port.input.InscricaoOpenInputPort;
import microservice.microinscricoes.application.port.output.InscricaoSaveOutputPort;
import microservice.microinscricoes.application.port.output.KafkaProducerFindIdTorneioOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class InscricaoOpenUseCase implements InscricaoOpenInputPort {

    private static final Logger log = LoggerFactory.getLogger(InscricaoOpenUseCase.class);

    private final InscricaoSaveOutputPort inscricaoSaveOutputPort;

    private final KafkaProducerFindIdTorneioOutputPort kafkaProducerFindIdTorneioOutputPort;

    public InscricaoOpenUseCase(InscricaoSaveOutputPort inscricaoSaveOutputPort,
                                KafkaProducerFindIdTorneioOutputPort kafkaProducerFindIdTorneioOutputPort) {
        this.inscricaoSaveOutputPort = inscricaoSaveOutputPort;
        this.kafkaProducerFindIdTorneioOutputPort = kafkaProducerFindIdTorneioOutputPort;
    }

    @Override
    public Inscricao open(Inscricao inscricao) {

        log.info("Iniciado serviço para abrir período de inscrições.");

        var inscricaoOpen = Optional.ofNullable(inscricao)
            .map(this::checkTournamentId)
            .map(this.inscricaoSaveOutputPort::save)
            .orElseThrow();

        log.info("Finalizado serviço para abrir período de inscrições, com Id: {}.", inscricaoOpen.getId());

        return inscricaoOpen;
    }

    private Inscricao checkTournamentId(Inscricao inscricao) {
        this.kafkaProducerFindIdTorneioOutputPort.sendFindIdTorneioEvent(inscricao);
        return inscricao;
    }
}

