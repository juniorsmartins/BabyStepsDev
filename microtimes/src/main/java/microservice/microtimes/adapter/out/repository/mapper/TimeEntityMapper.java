package microservice.microtimes.adapter.out.repository.mapper;

import microservice.microtimes.adapter.out.repository.entity.TimeEntity;
import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.core.domain.enums.ActivityStatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TimeEntityMapper {

    @Mapping(source = "activityStatus", target = "activityStatus", qualifiedByName = "setActivityStatus")
    TimeEntity toTimeEntity(Time time);

    @Named("setActivityStatus")
    default String setActivityStatus(ActivityStatusEnum activityStatusEnum) {
        return activityStatusEnum.getStatus();
    }

    @Mapping(source = "activityStatus", target = "activityStatus", qualifiedByName = "setStatus")
    Time toTime(TimeEntity timeEntity);

    @Named("setStatus")
    default ActivityStatusEnum setStatus(String activityStatusEnum) {
        return ActivityStatusEnum.toEnum(activityStatusEnum);
    }
}

