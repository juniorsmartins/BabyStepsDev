package microservice.microtimes.adapter.out.repository.mapper;

import microservice.microtimes.adapter.out.repository.entity.TimeEntity;
import microservice.microtimes.application.core.domain.Time;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOut {

//    @Mapping(source = "activityStatus", target = "activityStatus", qualifiedByName = "setActivityStatusString")
    TimeEntity toTimeEntity(Time time);

//    @Named("setActivityStatusString")
//    default String setActivityStatusString(ActivityStatusEnum activityStatus) {
//        return activityStatus.getStatus();
//    }

//    @Mapping(source = "activityStatus", target = "activityStatus", qualifiedByName = "setActivityStatusEnum")
    Time toTime(TimeEntity timeEntity);

//    @Named("setActivityStatusEnum")
//    default ActivityStatusEnum setActivityStatusEnum(String activityStatus) {
//        return ActivityStatusEnum.toEnum(activityStatus);
//    }
}

