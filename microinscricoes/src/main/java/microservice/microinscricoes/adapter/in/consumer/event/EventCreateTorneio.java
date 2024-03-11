package microservice.microinscricoes.adapter.in.consumer.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import microservice.microinscricoes.adapter.in.controller.dto.TorneioIdDto;

public record EventCreateTorneio(

    @NotNull
    @Valid
    TorneioIdDto torneio

) { }

