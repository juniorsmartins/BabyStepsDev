package microservice.microinscricoes.adapter.in.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record InscricaoOpenDtoIn(

    Long torneioId,

    String dataInicio,

    String dataFim,

    BigDecimal valor

) { }

