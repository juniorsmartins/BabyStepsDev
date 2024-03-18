package microservice.microtimes.adapter.mapper;

import microservice.microtimes.adapter.in.consumer.dto.HistoryDtoRequest;
import microservice.microtimes.adapter.in.consumer.dto.InscritoDtoRequest;
import microservice.microtimes.adapter.in.consumer.event.SagaEventRequest;
import microservice.microtimes.adapter.in.controller.dto.TorneioIdDto;
import microservice.microtimes.adapter.in.controller.dto.request.TimeCreateDtoRequest;
import microservice.microtimes.adapter.in.controller.dto.response.TimeCreateDtoResponse;
import microservice.microtimes.application.core.domain.History;
import microservice.microtimes.application.core.domain.Inscrito;
import microservice.microtimes.application.core.domain.SagaEvent;
import microservice.microtimes.application.core.domain.Time;
import microservice.microtimes.application.core.domain.value_object.TorneioVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperIn {

    Time toTime(TimeCreateDtoRequest timeCreateDtoRequest);

    TimeCreateDtoResponse toTimeCreateDtoResponse(Time time);

    @Mapping(source = "id", target = "sagaEventId")
    SagaEvent toSagaEvent(SagaEventRequest sagaEventRequest);

    @Mapping(source = "sagaEventId", target = "id")
    SagaEventRequest toSagaEventRequest(SagaEvent sagaEvent);

    TorneioVo toTorneioVo(TorneioIdDto torneioIdDto);

    TorneioIdDto toTorneioIdDto(TorneioVo torneioVo);

    @Mapping(source = "inscricao.id", target = "inscricaoId")
    Inscrito toInscrito(InscritoDtoRequest inscritoDtoRequest);

    History toHistory(HistoryDtoRequest historyDtoRequest);

}

