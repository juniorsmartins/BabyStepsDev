package microservice.microtorneios.adapters.mapper;

import microservice.microtorneios.adapters.out.repository.entity.TimeEntity;
import microservice.microtorneios.adapters.out.repository.entity.TorneioEntity;
import microservice.microtorneios.application.core.domain.Time;
import microservice.microtorneios.application.core.domain.Torneio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOut {

    TorneioEntity toTorneioEntity(Torneio torneio);

    Torneio toTorneio(TorneioEntity torneioEntity);

    TimeEntity toTimeEntity(Time time);

    Time toTime(TimeEntity timeEntity);
}

