package microservice.microtimes.adapter.in.controller.dto.request;

import java.time.Year;

public record ConquistaCreateDtoRequest(

    String titulo,

    Year ano

) { }

