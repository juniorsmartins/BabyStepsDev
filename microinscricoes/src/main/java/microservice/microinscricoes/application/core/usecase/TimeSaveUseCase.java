package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.Time;
import microservice.microinscricoes.application.port.input.TimeSaveInputPort;
import microservice.microinscricoes.application.port.output.TimeSaveOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TimeSaveUseCase implements TimeSaveInputPort {

    private static final Logger log = LoggerFactory.getLogger(TimeSaveUseCase.class);

    private final TimeSaveOutputPort timeSaveOutputPort;

    public TimeSaveUseCase(TimeSaveOutputPort timeSaveOutputPort) {
        this.timeSaveOutputPort = timeSaveOutputPort;
    }

    @Override
    public Time save(Time time) {

        log.info("Iniciado serviço para salvar Time.");

        var timeRegister = Optional.ofNullable(time)
            .map(this.timeSaveOutputPort::save)
            .orElseThrow();

        log.info("Finalizado serviço para salvar Time {}.", timeRegister);

        return timeRegister;
    }
}

