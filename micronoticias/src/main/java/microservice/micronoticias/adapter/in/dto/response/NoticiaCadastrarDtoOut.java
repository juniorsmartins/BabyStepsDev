package microservice.micronoticias.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record NoticiaCadastrarDtoOut(

    String chapeu,

    String titulo,

    String linhaFina,

    String lide,

    String corpo,

    List<String> autorias,

    List<String> fontes

) { }

