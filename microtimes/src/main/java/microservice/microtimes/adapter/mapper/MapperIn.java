package microservice.microtimes.adapter.mapper;

import microservice.microtimes.adapter.in.consumer.dto.HistoryDtoRequest;
import microservice.microtimes.adapter.in.consumer.dto.InscritoDtoRequest;
import microservice.microtimes.adapter.in.consumer.event.SagaEventRequest;
import microservice.microtimes.adapter.in.controller.dto.request.TimeCreateDtoRequest;
import microservice.microtimes.adapter.in.controller.dto.response.TimeCreateDtoResponse;
import microservice.microtimes.application.core.domain.History;
import microservice.microtimes.application.core.domain.Inscrito;
import microservice.microtimes.application.core.domain.SagaEvent;
import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.core.domain.enums.ESagaStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MapperIn {

    Time toTime(TimeCreateDtoRequest timeCreateDtoRequest);

    TimeCreateDtoResponse toTimeCreateDtoResponse(Time time);

    @Mapping(source = "id", target = "sagaEventId")
    @Mapping(source = "status", target = "status", qualifiedByName = "converterStringParaEnumSagaStatus")
    SagaEvent toSagaEvent(SagaEventRequest sagaEventRequest);

    @Mapping(source = "sagaEventId", target = "id")
    SagaEventRequest toSagaEventRequest(SagaEvent sagaEvent);

    @Mapping(source = "inscricao.id", target = "inscricaoId")
    Inscrito toInscrito(InscritoDtoRequest inscritoDtoRequest);

    @Mapping(source = "status", target = "status", qualifiedByName = "converterStringParaEnumSagaStatus")
    History toHistory(HistoryDtoRequest historyDtoRequest);

    @Named("converterStringParaEnumSagaStatus")
    default ESagaStatus converterStringParaEnumSagaStatus(String status) {
        return ESagaStatus.fromValue(status);
    }

}

