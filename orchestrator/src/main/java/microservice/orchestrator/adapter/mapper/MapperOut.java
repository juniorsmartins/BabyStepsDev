package microservice.orchestrator.adapter.mapper;

import microservice.orchestrator.adapter.in.consumer.dto.HistoryDtoRequest;
import microservice.orchestrator.adapter.in.consumer.dto.InscritoDtoRequest;
import microservice.orchestrator.adapter.in.consumer.dto.TimeIdDto;
import microservice.orchestrator.adapter.in.consumer.event.SagaEventRequest;
import microservice.orchestrator.application.core.domain.History;
import microservice.orchestrator.application.core.domain.Inscrito;
import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.core.domain.Time;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperOut {

    @Mapping(target = "id", source = "sagaEventId")
    SagaEventRequest toSagaEventRequest(SagaEvent sagaEvent);

    @Mapping(target = "inscricao.id", source = "inscricaoId")
    InscritoDtoRequest toInscritoDtoRequest(Inscrito inscrito);

    TimeIdDto toTimeIdDto(Time time);

    HistoryDtoRequest toHistoryDtoRequest(History history);

}

