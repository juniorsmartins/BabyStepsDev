package microservice.microtorneios.adapters.in.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TimeIdDto(@NotNull @Positive Long id) { }

