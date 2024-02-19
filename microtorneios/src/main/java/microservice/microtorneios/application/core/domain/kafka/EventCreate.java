package microservice.microtorneios.application.core.domain.kafka;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import microservice.microtorneios.adapters.in.dto.response.TorneioSaveDto;

public record EventCreate(

    @NotNull
    @Valid
    TorneioSaveDto torneio

) { }

