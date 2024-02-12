package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.Time;

public interface TimeSaveOutputPort {

    Time save(Time time);
}

