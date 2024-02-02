package microservice.microinscricoes.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record InscricaoOpenDtoOut(

    Long id,

    Long torneioId,

    String dataInicio,

    String dataFim,

    BigDecimal valor

) { }

