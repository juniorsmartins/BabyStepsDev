package microservice.microtorneios.adapters.in.mapper;

import microservice.microtorneios.adapters.in.dto.request.TorneioCreateDtoRequest;
import microservice.microtorneios.adapters.in.dto.response.TorneioCreateDtoResponse;
import microservice.microtorneios.application.core.domain.Torneio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Year;

@Mapper(componentModel = "spring")
public interface TorneioMapperIn {

    @Mapping(source = "ano", target = "ano", qualifiedByName = "setAno")
    Torneio toTorneio(TorneioCreateDtoRequest torneioCreateDtoRequest);

    @Named("setAno")
    default Year setAno(Integer ano) {
        return Year.of(ano);
    }

    TorneioCreateDtoResponse toTorneioCreateDtoResponse(Torneio torneio);
}

