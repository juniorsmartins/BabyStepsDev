package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.Inscrito;
import microservice.microinscricoes.application.port.StartSagaEventPort;
import microservice.microinscricoes.application.port.input.InscritoCreateInputPort;
import microservice.microinscricoes.application.port.output.InscricaoFindByIdOutputPort;
import microservice.microinscricoes.application.port.output.InscritoSaveOutputPort;
import microservice.microinscricoes.application.port.output.TimeFindByIdOutputPort;
import microservice.microinscricoes.config.exception.http_404.InscricaoNotFoundException;
import microservice.microinscricoes.config.exception.http_404.TimeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class InscritoCreateUseCase implements InscritoCreateInputPort {

    private static final Logger log = LoggerFactory.getLogger(InscritoCreateUseCase.class);

    private final InscritoSaveOutputPort inscritoSaveOutputPort;

    private final InscricaoFindByIdOutputPort inscricaoFindByIdOutputPort;

    private final TimeFindByIdOutputPort timeFindByIdOutputPort;

    private final StartSagaEventPort startSagaEventPort;

    public InscritoCreateUseCase(InscritoSaveOutputPort inscritoSaveOutputPort,
                                 InscricaoFindByIdOutputPort inscricaoFindByIdOutputPort,
                                 TimeFindByIdOutputPort timeFindByIdOutputPort,
                                 StartSagaEventPort startSagaEventPort) {
        this.inscritoSaveOutputPort = inscritoSaveOutputPort;
        this.inscricaoFindByIdOutputPort = inscricaoFindByIdOutputPort;
        this.timeFindByIdOutputPort = timeFindByIdOutputPort;
        this.startSagaEventPort = startSagaEventPort;
    }

    @Override
    public Inscrito create(Inscrito inscrito) {

        log.info("Iniciado serviço para criar novo Inscrito.");

        var inscritoRegistrado = Optional.ofNullable(inscrito)
            .map(this::checkInscricaoId)
            .map(this::checkTimeId)
            .map(this.inscritoSaveOutputPort::save)
            .map(this::startSagaEvent)
            .orElseThrow();

        log.info("Finalizado serviço para criar novo Inscrito, com Id: {}.", inscritoRegistrado.getId());

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

    private Inscrito startSagaEvent(Inscrito inscrito) {
        this.startSagaEventPort.sendEvent(inscrito);
        return inscrito;
    }
}

