package microservice.microtimes.adapter.out.producer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record EventCreateTime(

    @NotNull
    @Valid
    TimeSaveDto time

) { }

