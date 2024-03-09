package microservice.microtorneios.application.port.output;

import microservice.microtorneios.application.core.domain.Time;

public interface TimeSaveOutputPort {

    void save(Time time);
}

