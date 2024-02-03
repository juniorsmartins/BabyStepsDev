package microservice.microinscricoes.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record InscricaoOpenDtoOut(

    String id,

    Long torneioId,

    LocalDate dataInicio,

    LocalDate dataFim,

    BigDecimal valor

) { }

