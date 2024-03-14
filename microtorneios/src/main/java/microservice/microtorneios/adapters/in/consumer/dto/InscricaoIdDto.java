package microservice.microtorneios.adapters.in.consumer.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InscricaoIdDto(@NotNull @Positive Long id) { }

