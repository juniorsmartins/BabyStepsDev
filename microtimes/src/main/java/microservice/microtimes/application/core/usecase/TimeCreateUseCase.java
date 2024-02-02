package microservice.microtimes.application.core.usecase;

import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.core.domain.enums.ActivityStatusEnum;
import microservice.microtimes.application.port.input.TimeCreateInputPort;
import microservice.microtimes.application.port.output.TimeSaveOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TimeCreateUseCase implements TimeCreateInputPort {

    private static final Logger log = LoggerFactory.getLogger(TimeCreateUseCase.class);

    private final TimeSaveOutputPort saveOutputPort;

    public TimeCreateUseCase(TimeSaveOutputPort saveOutputPort) {
        this.saveOutputPort = saveOutputPort;
    }

    @Override
    public Time create(Time time) {

        log.info("Iniciado serviço para cadastrar novo Time.");

        var response = Optional.ofNullable(time)
            .map(this::addDefaultActivityStatus)
            .map(this.saveOutputPort::save)
            .orElseThrow();

        log.info("Finalizado serviço para cadastrar novo Time, com nome fantasia: {}.", response.getNomeFantasia());

        return response;
    }

    private Time addDefaultActivityStatus(Time time) {
        time.setStatus(ActivityStatusEnum.ATIVO);
        return time;
    }
}

