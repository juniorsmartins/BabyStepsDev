package microservice.microinscricoes.application.port.input;

import microservice.microinscricoes.application.core.domain.Time;

public interface TimeSaveInputPort {

    Time save(Time time);
}

