package microservice.microtimes.application.port.output;

import microservice.microtimes.adapter.out.producer.EventCreateTime;
import microservice.microtimes.application.core.domain.Time;

public interface NotifyCreationOfNewTimeOutputPort {

    void sendEvent(Time time);
}

