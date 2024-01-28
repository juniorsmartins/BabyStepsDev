package microservice.microtimes.application.port.output;

import microservice.microtimes.application.core.domain.Time;

public interface TimeSaveOutputPort {

    Time save(Time time);
}

