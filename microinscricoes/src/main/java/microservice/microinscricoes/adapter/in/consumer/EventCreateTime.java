package microservice.microinscricoes.adapter.in.consumer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record EventCreateTime(

    @NotNull
    @Valid
    TimeSaveDto time

) { }

