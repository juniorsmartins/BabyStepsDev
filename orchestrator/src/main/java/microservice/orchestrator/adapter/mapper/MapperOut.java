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

    @Mapping(source = "sagaEventId", target = "id")
    SagaEventRequest toSagaEventRequest(SagaEvent sagaEvent);

    @Mapping(source = "inscricaoId", target = "inscricao.id")
    InscritoDtoRequest toInscritoDtoRequest(Inscrito inscrito);

    TimeIdDto toTimeIdDto(Time time);

    HistoryDtoRequest toHistoryDtoRequest(History history);

}

