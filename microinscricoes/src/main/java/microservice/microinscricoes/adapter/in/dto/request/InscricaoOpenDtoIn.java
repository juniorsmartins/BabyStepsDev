package microservice.microinscricoes.adapter.in.dto.request;

import java.math.BigDecimal;

public record InscricaoOpenDtoIn(

    Long torneioId,

    String dataInicio,

    String dataFim,

    BigDecimal valor

) { }

