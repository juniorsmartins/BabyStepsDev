package microservice.microtimes.application.core.usecase;

import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.port.input.TimeCreateInputPort;
import microservice.microtimes.application.port.output.NotifyCreateTimeOutputPort;
import microservice.microtimes.application.port.output.TimeSaveOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.Optional;

public class TimeCreateUseCase implements TimeCreateInputPort {

    private static final Logger log = LoggerFactory.getLogger(TimeCreateUseCase.class);

    private final TimeSaveOutputPort timeSaveOutputPort;

    private final NotifyCreateTimeOutputPort notifyCreateTimeOutputPort;

    public TimeCreateUseCase(TimeSaveOutputPort timeSaveOutputPort,
                             NotifyCreateTimeOutputPort notifyCreateTimeOutputPort) {
        this.timeSaveOutputPort = timeSaveOutputPort;
        this.notifyCreateTimeOutputPort = notifyCreateTimeOutputPort;
    }

    @Override
    public Time create(Time time) {

        log.info("Iniciado serviço para cadastrar novo Time.");

        var response = Optional.ofNullable(time)
            .map(this.timeSaveOutputPort::save)
            .map(this::notifyCreateTime)
            .orElseThrow();

        log.info("Finalizado serviço para cadastrar novo Time, com nome fantasia: {}.", response.getNomeFantasia());

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

