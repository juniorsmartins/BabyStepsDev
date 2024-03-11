package microservice.microtorneios.adapters.mapper;

import microservice.microtorneios.adapters.in.controller.dto.TimeIdDto;
import microservice.microtorneios.adapters.in.consumer.event.SagaEventRequest;
import microservice.microtorneios.adapters.in.controller.dto.request.TorneioCreateDtoRequest;
import microservice.microtorneios.adapters.in.controller.dto.response.TorneioCreateDtoResponse;
import microservice.microtorneios.application.core.domain.SagaEvent;
import microservice.microtorneios.application.core.domain.Time;
import microservice.microtorneios.application.core.domain.Torneio;
import microservice.microtorneios.application.core.domain.enums.ESagaStatus;
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

    Time toTime(TimeIdDto timeIdDto);

    TimeIdDto toTimeIdDto(Time time);

    @Mapping(source = "id", target = "sagaEventId")
    @Mapping(source = "status", target = "status", qualifiedByName = "converterStringParaEnumSagaStatus")
    SagaEvent toSagaEvent(SagaEventRequest sagaEventRequest);

    @Mapping(source = "sagaEventId", target = "id")
    SagaEventRequest toSagaEventRequest(SagaEvent sagaEvent);

    @Named("converterStringParaEnumSagaStatus")
    default ESagaStatus converterStringParaEnumSagaStatus(String status) {
        if (status == null) {
            return null;
        }
        return ESagaStatus.fromValue(status);
    }

}

