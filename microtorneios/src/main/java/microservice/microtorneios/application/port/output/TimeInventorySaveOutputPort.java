package microservice.microtorneios.application.port.output;

import microservice.microtorneios.application.core.domain.Time;

public interface TimeInventorySaveOutputPort {

    void save(Time time);
}

