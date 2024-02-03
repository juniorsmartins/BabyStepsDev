package microservice.microinscricoes.adapter.in.dto;

import jakarta.validation.constraints.NotNull;
import microservice.microinscricoes.application.core.domain.enums.ETipoPagamento;

public record PagamentoDto(

    @NotNull
    Integer numeroBanco,

    @NotNull
    Integer numeroAgencia,

    @NotNull
    Integer numeroCartao,

    @NotNull
    ETipoPagamento tipo
) { }

