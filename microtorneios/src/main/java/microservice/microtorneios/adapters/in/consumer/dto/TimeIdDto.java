package microservice.microtorneios.adapters.in.consumer.dto;

import jakarta.validation.constraints.Positive;

public record TimeIdDto(@Positive Long id) { }

