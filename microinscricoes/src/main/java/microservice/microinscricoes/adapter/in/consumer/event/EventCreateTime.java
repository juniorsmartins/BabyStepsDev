package microservice.microinscricoes.adapter.in.consumer.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import microservice.microinscricoes.adapter.in.consumer.dto.TimeSaveDto;

public record EventCreateTime(

    @NotNull
    @Valid
    TimeSaveDto time

) { }

