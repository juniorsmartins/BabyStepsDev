package microservice.microtorneios.adapters.out.producer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record EventCreateTorneio(

    @NotNull
    @Valid
    TorneioSaveDto torneio

) { }

