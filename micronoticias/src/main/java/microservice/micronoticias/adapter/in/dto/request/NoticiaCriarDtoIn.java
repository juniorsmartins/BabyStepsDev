package microservice.micronoticias.adapter.in.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import microservice.micronoticias.application.core.domain.Noticia;

import java.util.List;
import java.util.Set;

@Builder
public record NoticiaCriarDtoIn(

    @NotBlank
    @Size(min = Noticia.CHAPEU_CARACTERES_MINIMO, max = Noticia.CHAPEU_CARACTERES_MAXIMO)
    String chapeu,

    @NotBlank
    @Size(min = Noticia.TITULO_CARACTERES_MINIMO, max = Noticia.TITULO_CARACTERES_MAXIMO)
    String titulo,

    @NotBlank
    @Size(min = Noticia.LINHAFINA_CARACTERES_MINIMO, max = Noticia.LINHAFINA_CARACTERES_MAXIMO)
    String linhaFina,

    @NotBlank
    @Size(min = Noticia.LIDE_CARACTERES_MINIMO, max = Noticia.LIDE_CARACTERES_MAXIMO)
    String lide,

    @NotBlank
    @Size(min = Noticia.CORPO_CARACTERES_MINIMO, max = Noticia.CORPO_CARACTERES_MAXIMO)
    String corpo,

    List<@NotBlank @Size(min = Noticia.AUTORIA_CARACTERES_MINIMO, max = Noticia.AUTORIA_CARACTERES_MAXIMO) String> autorias,

    List<@NotBlank @Size(min = Noticia.FONTE_CARACTERES_MINIMO, max = Noticia.FONTE_CARACTERES_MAXIMO) String> fontes,

    @Valid
    Set<@NotNull EditoriaCriarDtoIn> editorias

) { }

