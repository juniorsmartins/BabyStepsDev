package microservice.microtimes.adapter.utils;

import microservice.microtimes.adapter.out.producer.event.EventCreateTime;
import microservice.microtimes.application.core.domain.Time;

public interface EncapsulateEvent {

    EventCreateTime toEventCreateTime(Time time);
}

