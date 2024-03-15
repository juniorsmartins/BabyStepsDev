package microservice.orchestrator.adapter.mapper;

import microservice.microtimes.adapter.out.repository.entity.TimeEntity;
import microservice.microtimes.adapter.out.repository.entity.value_object.TorneioDb;
import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.core.domain.value_object.TorneioVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOut {

    TimeEntity toTimeEntity(Time time);

    Time toTime(TimeEntity timeEntity);

    TorneioDb toTorneioDb(TorneioVo torneioVo);

    TorneioVo toTorneioVo(TorneioDb torneioDb);

}

