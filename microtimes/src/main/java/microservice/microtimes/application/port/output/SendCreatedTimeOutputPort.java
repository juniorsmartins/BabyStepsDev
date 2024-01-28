package microservice.microtimes.application.port.output;

import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.core.domain.enums.TimeEventEnum;

public interface SendCreatedTimeOutputPort {

    void send(Time time, TimeEventEnum event);
}

