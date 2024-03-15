package microservice.orchestrator.adapter.mapper;

import microservice.microtimes.adapter.out.repository.entity.TimeEntity;
import microservice.microtimes.adapter.out.repository.entity.value_object.TorneioDb;
import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.core.domain.value_object.TorneioVo;
import microservice.orchestrator.adapter.in.consumer.event.SagaEventRequest;
import microservice.orchestrator.application.core.domain.SagaEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperOut {

    TimeEntity toTimeEntity(Time time);

    Time toTime(TimeEntity timeEntity);

    TorneioDb toTorneioDb(TorneioVo torneioVo);

    TorneioVo toTorneioVo(TorneioDb torneioDb);

    @Mapping(source = "sagaEventId", target = "id")
    SagaEventRequest toSagaEventRequest(SagaEvent sagaEvent);

}

