package microservice.microtorneios.adapters.in.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import microservice.microtorneios.adapters.in.controller.dto.TimeIdDto;

import java.util.Set;

@Builder
public record TorneioCreateDtoRequest(

    @NotBlank
    String nome,

    @NotNull
    @Positive
    Integer ano,

    @Valid
    Set<TimeIdDto> times

) { }

