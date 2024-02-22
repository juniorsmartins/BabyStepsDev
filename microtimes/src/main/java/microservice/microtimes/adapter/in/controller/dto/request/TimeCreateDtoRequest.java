package microservice.microtimes.adapter.in.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TimeCreateDtoRequest(

    @NotBlank
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

    String headCoach

) { }

