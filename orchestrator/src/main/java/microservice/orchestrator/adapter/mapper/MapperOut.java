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

    @Mapping(source = "sagaEventId", target = "id")
    @Mapping(source = "source", target = "source", qualifiedByName = "converterEnumEventSourceParaString")
    @Mapping(source = "status", target = "status", qualifiedByName = "converterEnumSagaStatusParaString")
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

    @Mapping(source = "inscricaoId", target = "inscricao.id")
    InscritoDtoRequest toInscritoDtoRequest(Inscrito inscrito);

    TimeIdDto toTimeIdDto(Time time);

    @Mapping(source = "status", target = "status", qualifiedByName = "converterEnumSagaStatusParaString")
    HistoryDtoRequest toHistoryDtoRequest(History history);

}

