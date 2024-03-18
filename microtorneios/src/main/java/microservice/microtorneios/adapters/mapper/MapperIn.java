package microservice.microtorneios.adapters.mapper;

import microservice.microtorneios.adapters.in.consumer.event.SagaEventRequest;
import microservice.microtorneios.adapters.in.controller.dto.TimeIdDto;
import microservice.microtorneios.adapters.in.controller.dto.request.TorneioCreateDtoRequest;
import microservice.microtorneios.adapters.in.controller.dto.response.TorneioCreateDtoResponse;
import microservice.microtorneios.application.core.domain.SagaEvent;
import microservice.microtorneios.application.core.domain.Torneio;
import microservice.microtorneios.application.core.domain.value_object.TimeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Year;

@Mapper(componentModel = "spring")
public interface MapperIn {

    @Mapping(source = "ano", target = "ano", qualifiedByName = "setAno")
    Torneio toTorneio(TorneioCreateDtoRequest torneioCreateDtoRequest);

    @Named("setAno")
    default Year setAno(Integer ano) {
        return Year.of(ano);
    }

    TorneioCreateDtoResponse toTorneioCreateDtoResponse(Torneio torneio);

    TimeVo toTime(TimeIdDto timeIdDto);

    TimeIdDto toTimeIdDto(TimeVo timeVo);

    @Mapping(source = "id", target = "sagaEventId")
    SagaEvent toSagaEvent(SagaEventRequest sagaEventRequest);

    @Mapping(source = "sagaEventId", target = "id")
    SagaEventRequest toSagaEventRequest(SagaEvent sagaEvent);

}

