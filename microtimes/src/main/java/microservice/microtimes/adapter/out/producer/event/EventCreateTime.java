package microservice.microtimes.adapter.out.producer.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import microservice.microtimes.adapter.out.producer.dto.TimeSaveDto;

public record EventCreateTime(

    @NotNull
    @Valid
    TimeSaveDto time

) { }

