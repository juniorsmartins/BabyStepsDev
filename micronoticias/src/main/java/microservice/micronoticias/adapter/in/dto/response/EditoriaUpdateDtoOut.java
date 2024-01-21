package microservice.micronoticias.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "nomenclatura", "descricao"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record EditoriaUpdateDtoOut(

    @JsonProperty("id")
    Long id,

    String nomenclatura,

    String descricao

) { }

