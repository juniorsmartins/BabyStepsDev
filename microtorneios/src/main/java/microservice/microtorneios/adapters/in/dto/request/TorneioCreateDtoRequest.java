package microservice.microtorneios.adapters.in.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record TorneioCreateDtoRequest(

    @NotBlank
    String nome,

    @NotNull
    @Size(min = 1800)
    Integer ano,

    @Valid
    Set<TimeInventoryDtoId> times

) { }

