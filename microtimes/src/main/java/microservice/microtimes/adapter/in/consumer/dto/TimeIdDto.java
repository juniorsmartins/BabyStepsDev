package microservice.microtimes.adapter.in.consumer.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TimeIdDto(

    @NotNull
    @Positive
    Long id

) { }

