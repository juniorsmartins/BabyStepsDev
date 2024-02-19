package microservice.microtimes.adapter.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.out.producer.EventCreateTime;
import microservice.microtimes.adapter.out.producer.TimeSaveDto;
import microservice.microtimes.application.core.domain.Time;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class EncapsulateEventImpl implements EncapsulateEvent {

    @Override
    public EventCreateTime toEventCreateTime(final Time time) {

        return Optional.ofNullable(time)
            .map(team -> new TimeSaveDto(team.getId(), team.getNomeFantasia()))
            .map(EventCreateTime::new)
            .orElseThrow();
    }
}

