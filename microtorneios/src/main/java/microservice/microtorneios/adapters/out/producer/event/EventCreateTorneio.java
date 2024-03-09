package microservice.microtorneios.adapters.out.producer.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import microservice.microtorneios.adapters.out.producer.dto.TorneioSaveDto;

public record EventCreateTorneio(

    @NotNull
    @Valid
    TorneioSaveDto torneio

) { }

