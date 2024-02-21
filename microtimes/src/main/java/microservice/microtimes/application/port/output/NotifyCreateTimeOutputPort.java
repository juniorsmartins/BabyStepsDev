package microservice.microtimes.application.port.output;

import microservice.microtimes.application.core.domain.Time;

public interface NotifyCreateTimeOutputPort {

    void sendEvent(Time time);
}

