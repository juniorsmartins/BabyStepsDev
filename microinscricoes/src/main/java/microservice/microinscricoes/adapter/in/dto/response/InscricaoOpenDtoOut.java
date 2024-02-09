package microservice.microinscricoes.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import microservice.microinscricoes.application.core.domain.enums.EInscricaoStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record InscricaoOpenDtoOut(

    Long id,

    Long torneioId,

    LocalDate dataInicio,

    LocalDate dataFim,

    BigDecimal valor,

    EInscricaoStatus inscricaoStatus

) { }

