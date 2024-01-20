package microservice.micronoticias.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
@JsonPropertyOrder({"id", "chapeu", "titulo", "linhaFina", "lide", "corpo", "autorias", "fontes", "editorias"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record NoticiaCriarDtoOut(

    @JsonProperty("id")
    Long id,

    String chapeu,

    String titulo,

    String linhaFina,

    String lide,

    String corpo,

    List<String> autorias,

    List<String> fontes,

    Set<EditoriaCriarDtoOut> editorias

) { }

