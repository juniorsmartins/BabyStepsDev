package microservice.microtimes.adapter.out.producer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TimeSaveDto(

    @NotNull
    @Positive
    Long id,

    @NotBlank
    String nomeFantasia

) { }
