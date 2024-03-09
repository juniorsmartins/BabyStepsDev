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

    LocalDate data,

    String descricao,

//    Set<String> categoriasEsportivas,

    // ---------- SEDE ----------

//    String cep,

    String estado,

    String cidade,

//    String bairro,

//    String logradouro,

//    String numero,

//    String complemento,


    // ---------- Contato ----------
//    String email,

//    String telefone,

//    String site,


    // ---------- Redes Sociais ----------
//    Set<String> redesSociais,


    // ---------- Staff ----------
    String presidente,

    String vicePresidente,

    String headCoach


    // ---------- Conquista ----------
//    Set<ConquistaCreateDtoRequest> conquistas

) { }

