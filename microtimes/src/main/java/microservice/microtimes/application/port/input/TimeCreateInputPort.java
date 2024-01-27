package microservice.microtimes.application.port.input;

import microservice.microtimes.application.core.domain.Time;

public interface TimeCreateInputPort {

    Time create(Time time);
}

