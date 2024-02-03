package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.Inscrito;
import microservice.microinscricoes.application.port.input.InscritoRegisterInputPort;
import microservice.microinscricoes.application.port.output.InscritoSaveOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class InscritoRegisterUseCase implements InscritoRegisterInputPort {

    private static final Logger log = LoggerFactory.getLogger(InscritoRegisterUseCase.class);

    private final InscritoSaveOutputPort inscritoSaveOutputPort;

    public InscritoRegisterUseCase(InscritoSaveOutputPort inscritoSaveOutputPort) {
        this.inscritoSaveOutputPort = inscritoSaveOutputPort;
    }

    @Override
    public Inscrito register(Inscrito inscrito) {

        log.info("Iniciado serviço para registrar novo Inscrito.");

        var inscritoRegistrado = Optional.ofNullable(inscrito)
            .map(this.inscritoSaveOutputPort::save)
            .orElseThrow();

        log.info("Finalizado serviço para registrar novo Inscrito, com Id: {}.", inscritoRegistrado.getId());

        return inscritoRegistrado;
    }
}

