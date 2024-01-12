package microservice.micronoticias.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record NoticiaCriarDtoOut(

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

