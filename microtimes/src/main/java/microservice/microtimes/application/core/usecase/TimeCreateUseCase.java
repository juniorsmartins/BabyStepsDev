package microservice.microtimes.application.core.usecase;

import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.port.input.TimeCreateInputPort;
import microservice.microtimes.application.port.output.CarteiroNotifyCreatedTimeOutputPort;
import microservice.microtimes.application.port.output.TimeSaveOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.Optional;

public class TimeCreateUseCase implements TimeCreateInputPort {

    private static final Logger log = LoggerFactory.getLogger(TimeCreateUseCase.class);

    private final TimeSaveOutputPort timeSaveOutputPort;

    private final CarteiroNotifyCreatedTimeOutputPort notifyCreateTimeOutputPort;

    public TimeCreateUseCase(TimeSaveOutputPort timeSaveOutputPort,
                             CarteiroNotifyCreatedTimeOutputPort notifyCreateTimeOutputPort) {
        this.timeSaveOutputPort = timeSaveOutputPort;
        this.notifyCreateTimeOutputPort = notifyCreateTimeOutputPort;
    }

    @Override
    public Time create(Time time) {

        log.info("Serviço iniciado para criar novo Time.");

        var response = Optional.ofNullable(time)
            .map(this.timeSaveOutputPort::save)
            .map(this::notifyCreateTime)
            .orElseThrow();

        log.info("Serviço finalizado para criar novo Time: {}.", response);

        return response;
    }

    private Time notifyCreateTime(Time time) {

        Optional.ofNullable(time)
            .ifPresentOrElse(this.notifyCreateTimeOutputPort::sendEvent,
                () -> {throw new NoSuchElementException();}
            );

        return time;
    }
}

