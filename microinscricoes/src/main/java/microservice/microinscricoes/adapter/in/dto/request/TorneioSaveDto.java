package microservice.microinscricoes.adapter.in.dto.request;

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

