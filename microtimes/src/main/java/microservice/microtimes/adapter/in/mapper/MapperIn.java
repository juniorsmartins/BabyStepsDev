package microservice.microtimes.adapter.in.mapper;

import microservice.microtimes.adapter.in.controller.dto.request.TimeCreateDtoRequest;
import microservice.microtimes.adapter.in.controller.dto.response.TimeCreateDtoResponse;
import microservice.microtimes.application.core.domain.Time;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperIn {

    @Mapping(target = "status", ignore = true)
    Time toTime(TimeCreateDtoRequest timeCreateDtoRequest);

    TimeCreateDtoResponse toTimeCreateDtoResponse(Time time);
}

