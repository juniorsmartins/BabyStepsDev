package microservice.microtorneios.adapters.mapper;

import microservice.microtorneios.adapters.out.repository.entity.TorneioEntity;
import microservice.microtorneios.adapters.out.repository.entity.value_object.TimeDb;
import microservice.microtorneios.application.core.domain.value_object.TimeVo;
import microservice.microtorneios.application.core.domain.Torneio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOut {

    TorneioEntity toTorneioEntity(Torneio torneio);

    Torneio toTorneio(TorneioEntity torneioEntity);

    TimeDb toTimeDb(TimeVo timeVo);

    TimeVo toTimeVo(TimeDb timeDb);
}

