package microservice.microtorneios.adapters.in.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import microservice.microtorneios.application.core.domain.enums.ActivityStatusEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TimeInventoryCreateDtoResponse(

    Long id,

    String nomeFantasia,

    String estado,

    ActivityStatusEnum status

) { }

