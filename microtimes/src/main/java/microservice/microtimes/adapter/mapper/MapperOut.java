package microservice.microtimes.adapter.mapper;

import microservice.microtimes.adapter.out.repository.entity.TimeEntity;
import microservice.microtimes.application.core.domain.Time;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOut {

    TimeEntity toTimeEntity(Time time);

    Time toTime(TimeEntity timeEntity);

}

