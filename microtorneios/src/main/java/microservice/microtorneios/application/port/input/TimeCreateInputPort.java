package microservice.microtorneios.application.port.input;

import microservice.microtorneios.application.core.domain.Time;

public interface TimeCreateInputPort {

    void create(Time time);
}

