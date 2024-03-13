package microservice.microtimes.adapter.in.controller.dto.response;

import microservice.microtimes.adapter.in.controller.dto.TorneioIdDto;
import microservice.microtimes.application.core.domain.value_object.TorneioVo;

import java.time.LocalDate;
import java.util.Set;

public record TimeCreateDtoResponse(

    Long id,

    String nomeFantasia,

    String razaoSocial,

    String cnpj,


    // ---------- SEDE ----------
    String estado,

    String cidade,


    // ---------- Fundação ----------
    LocalDate data,

    String descricao,


    // ---------- Staff ----------
    String presidente,

    String vicePresidente,

    String headCoach,


    // ---------- Torneios ----------
    Set<TorneioIdDto> torneios

) { }

