package microservice.microtorneios.adapters.in.dto.response;

import java.time.Year;
import java.util.Set;

public record TorneioCreateDtoResponse(

    Long id,

    String nome,

    Year ano,

    Set<TimeInventoryCreateDtoResponse> times

) { }

