package microservice.microinscricoes.adapter.in.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TimeIdDto(

    @NotNull
    @Positive
    Long id

) { }

