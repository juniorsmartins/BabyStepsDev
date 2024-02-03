package microservice.microinscricoes.adapter.in.dto;

import microservice.microinscricoes.application.core.domain.enums.ETipoPagamento;

public record PagamentoDtoIn(

    Integer numeroBanco,

    Integer numeroAgencia,

    Integer numeroCartao,

    ETipoPagamento tipo
) { }

