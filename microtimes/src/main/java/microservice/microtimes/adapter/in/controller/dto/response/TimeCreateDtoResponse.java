package microservice.microtimes.adapter.in.controller.dto.response;

import microservice.microtimes.application.core.domain.enums.ActivityStatusEnum;

import java.time.LocalDate;

public record TimeCreateDtoResponse(

    Long id,

    String nomeFantasia,

    String razaoSocial,

    String cnpj,

    ActivityStatusEnum status,


    // ---------- SEDE ----------
    String estado,

    String cidade,


    // ---------- Fundação ----------
    LocalDate data,

    String descricao,


    // ---------- Staff ----------
    String presidente,

    String vicePresidente,

    String headCoach

) { }

