package microservice.microtorneios.adapters.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TorneioCreateDtoRequest(

    @NotBlank
    String nome,

    @NotNull
    @Size(min = 1500)
    Integer ano

) { }

