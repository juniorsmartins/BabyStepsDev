package microservice.microtimes.adapter.in.mapper;

import microservice.microtimes.adapter.in.dto.request.TimeCreateDtoRequest;
import microservice.microtimes.adapter.in.dto.response.TimeCreateDtoResponse;
import microservice.microtimes.application.core.domain.Time;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeRequestMapper {

    Time toTime(TimeCreateDtoRequest timeCreateDtoRequest);

    TimeCreateDtoResponse toTimeCreateDtoResponse(Time time);
}

