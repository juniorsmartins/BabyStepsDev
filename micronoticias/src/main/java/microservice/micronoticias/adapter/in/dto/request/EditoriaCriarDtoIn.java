package microservice.micronoticias.adapter.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import microservice.micronoticias.application.core.domain.Editoria;

@Builder
public record EditoriaCriarDtoIn(

    @NotBlank
    @Size(min = Editoria.NOMENCLATURA_CARACTERES_MININO, max = Editoria.NOMENCLATURA_CARACTERES_MAXIMO)
    String nomenclatura,

    @NotBlank
    @Size(min = Editoria.DESCRICAO_CARACTERES_MININO, max = Editoria.DESCRICAO_CARACTERES_MAXIMO)
    String descricao

) { }

