package microservice.microtorneios.adapters.out.repository.mapper;

import microservice.microtorneios.adapters.out.repository.entity.TorneioEntity;
import microservice.microtorneios.application.core.domain.Torneio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TorneioMapperOut {

    TorneioEntity toTorneioEntity(Torneio torneio);

    Torneio toTorneio(TorneioEntity torneioEntity);
}

