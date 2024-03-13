package microservice.microtimes.adapter.in.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TorneioIdDto(@NotNull @Positive Long id) { }

