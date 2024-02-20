package microservice.microtorneios.adapters.out.producer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Year;

public record TorneioSaveDto(

    @NotNull
    @Positive
    Long id,

    @NotBlank
    String nome,

    @NotNull
    Year ano

) { }
