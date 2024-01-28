package microservice.microtorneios.adapters.in.dto.response;

import java.time.Year;

public record TorneioCreateDtoResponse(

    Long id,

    String nome,

    Year ano

) { }

