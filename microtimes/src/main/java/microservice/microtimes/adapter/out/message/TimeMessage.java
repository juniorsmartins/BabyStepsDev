package microservice.microtimes.adapter.out.message;

import lombok.*;
import microservice.microtimes.application.core.domain.Time;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeMessage {

    private Time time;
}

