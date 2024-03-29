package microservice.microtimes.adapter.out.producer.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import microservice.microtimes.adapter.in.controller.dto.TimeIdDto;

public record EventCreateTime(

    @NotNull
    @Valid
    TimeIdDto time

) { }

