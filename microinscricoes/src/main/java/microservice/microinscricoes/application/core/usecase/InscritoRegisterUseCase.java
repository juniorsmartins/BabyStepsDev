package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.Inscrito;
import microservice.microinscricoes.application.port.input.InscritoRegisterInputPort;
import microservice.microinscricoes.application.port.output.InscricaoFindByIdOutputPort;
import microservice.microinscricoes.application.port.output.InscritoSaveOutputPort;
import microservice.microinscricoes.application.port.output.TimeFindByIdOutputPort;
import microservice.microinscricoes.config.exception.http_404.InscricaoNotFoundException;
import microservice.microinscricoes.config.exception.http_404.TimeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class InscritoRegisterUseCase implements InscritoRegisterInputPort {

    private static final Logger log = LoggerFactory.getLogger(InscritoRegisterUseCase.class);

    private final InscritoSaveOutputPort inscritoSaveOutputPort;

    private final InscricaoFindByIdOutputPort inscricaoFindByIdOutputPort;

    private final TimeFindByIdOutputPort timeFindByIdOutputPort;

    public InscritoRegisterUseCase(InscritoSaveOutputPort inscritoSaveOutputPort,
                                   InscricaoFindByIdOutputPort inscricaoFindByIdOutputPort,
                                   TimeFindByIdOutputPort timeFindByIdOutputPort) {
        this.inscritoSaveOutputPort = inscritoSaveOutputPort;
        this.inscricaoFindByIdOutputPort = inscricaoFindByIdOutputPort;
        this.timeFindByIdOutputPort = timeFindByIdOutputPort;
    }

    @Override
    public Inscrito register(Inscrito inscrito) {

        log.info("Iniciado serviço para registrar novo Inscrito.");

        var inscritoRegistrado = Optional.ofNullable(inscrito)
            .map(this::checkInscricaoId)
            .map(this::checkTimeId)
            .map(this.inscritoSaveOutputPort::save)
            .orElseThrow();

        log.info("Finalizado serviço para registrar novo Inscrito, com Id: {}.", inscritoRegistrado.getId());

        return inscritoRegistrado;
    }

    private Inscrito checkInscricaoId(Inscrito inscrito) {
        var inscricaoId = inscrito.getInscricao().getId();

        var inscricao = this.inscricaoFindByIdOutputPort.findById(inscricaoId)
            .orElseThrow(() -> new InscricaoNotFoundException(inscricaoId));

        inscrito.setInscricao(inscricao);

        return inscrito;
    }

    private Inscrito checkTimeId(Inscrito inscrito) {
        var timeId = inscrito.getTime().getId();

        var time = this.timeFindByIdOutputPort.findById(timeId)
            .orElseThrow(() -> new TimeNotFoundException(timeId));

        inscrito.setTime(time);

        return inscrito;
    }
}

