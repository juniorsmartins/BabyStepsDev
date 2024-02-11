package microservice.microtorneios.adapters.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Year;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TorneioCreateDtoResponse(

    Long id,

    String nome,

    Year ano,

    Set<TimeInventoryCreateDtoResponse> times

) { }

