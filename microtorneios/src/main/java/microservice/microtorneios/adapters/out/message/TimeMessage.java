package microservice.microtorneios.adapters.out.message;

import lombok.*;
import microservice.microtorneios.application.core.domain.Time;
import microservice.microtorneios.application.core.domain.enums.TimeEventEnum;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeMessage {

    private Time time;

    private TimeEventEnum event;
}

