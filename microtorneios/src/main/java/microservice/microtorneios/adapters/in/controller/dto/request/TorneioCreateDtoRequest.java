package microservice.microtorneios.adapters.in.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record TorneioCreateDtoRequest(

    @NotBlank
    String nome,

    @NotNull
    @Positive
    Integer ano

) { }

