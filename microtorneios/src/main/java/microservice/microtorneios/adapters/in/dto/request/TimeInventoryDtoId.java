package microservice.microtorneios.adapters.in.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TimeInventoryDtoId(

    @NotNull
    @Positive
    Long id

) { }

