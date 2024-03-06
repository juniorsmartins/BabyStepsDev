package microservice.microtimes.adapter.mapper;

import microservice.microtimes.adapter.out.repository.entity.TimeEntity;
import microservice.microtimes.adapter.out.repository.entity.ValidationEntity;
import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.core.domain.ValidationModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOut {

    TimeEntity toTimeEntity(Time time);

    Time toTime(TimeEntity timeEntity);

    ValidationEntity toValidationEntity(ValidationModel validationModel);

    ValidationModel toValidationModel(ValidationEntity validationEntity);

}

