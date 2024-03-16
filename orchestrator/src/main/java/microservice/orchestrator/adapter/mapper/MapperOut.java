package microservice.orchestrator.adapter.mapper;

import microservice.orchestrator.adapter.in.consumer.dto.HistoryDtoRequest;
import microservice.orchestrator.adapter.in.consumer.dto.InscritoDtoRequest;
import microservice.orchestrator.adapter.in.consumer.dto.TimeIdDto;
import microservice.orchestrator.adapter.in.consumer.event.SagaEventRequest;
import microservice.orchestrator.application.core.domain.History;
import microservice.orchestrator.application.core.domain.Inscrito;
import microservice.orchestrator.application.core.domain.SagaEvent;
import microservice.orchestrator.application.core.domain.Time;
import microservice.orchestrator.application.core.domain.enums.EEventSource;
import microservice.orchestrator.application.core.domain.enums.ESagaStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MapperOut {

    @Mapping(target = "id", source = "sagaEventId")
    @Mapping(target = "source", source = "source", qualifiedByName = "converterEnumEventSourceParaString")
    @Mapping(target = "status", source = "status", qualifiedByName = "converterEnumSagaStatusParaString")
    SagaEventRequest toSagaEventRequest(SagaEvent sagaEvent);

    @Named("converterEnumEventSourceParaString")
    default String converterEnumEventSourceParaString(EEventSource source) {
        if (source == null) {
            return null;
        }
        return source.getValue();
    }

    @Named("converterEnumSagaStatusParaString")
    default String converterEnumSagaStatusParaString(ESagaStatus status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Mapping(target = "inscricao.id", source = "inscricaoId")
    InscritoDtoRequest toInscritoDtoRequest(Inscrito inscrito);

    TimeIdDto toTimeIdDto(Time time);

    @Mapping(target = "status", source = "status", qualifiedByName = "converterEnumSagaStatusParaString")
    HistoryDtoRequest toHistoryDtoRequest(History history);

}

