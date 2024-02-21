package microservice.microinscricoes.adapter.in.controller.dto;

import jakarta.validation.constraints.Positive;

public record TimeIdDto(@Positive Long id) { }

