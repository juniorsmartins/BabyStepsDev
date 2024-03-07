package microservice.micropagamentos.adapter.in.consumer.dto;

import jakarta.validation.constraints.Positive;

public record TimeIdDto(@Positive Long id) { }

