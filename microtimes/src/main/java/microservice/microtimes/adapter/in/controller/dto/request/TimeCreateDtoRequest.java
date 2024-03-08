package microservice.microtimes.adapter.in.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record TimeCreateDtoRequest(

    @NotBlank
    String nomeFantasia,

    String razaoSocial,

    String cnpj,

    LocalDate dataFundacao,

    Set<String> categoriasEsportivas,

    // ---------- SEDE ----------

    String cep,

    String estado,

    String cidade,

    String bairro,

    String logradouro,

    String numero,

    String complemento,


    // ---------- Contato ----------
    String email,

    String telefone,

    String site,


    // ---------- Redes Sociais ----------
    Set<String> redesSociais,


    // ---------- Staff ----------
    String nomePresidente,

    String nomeVicePresidente,

    String nomeHeadCoach,


    // ---------- Conquista ----------
    Set<ConquistaCreateDtoRequest> conquistas

) { }

