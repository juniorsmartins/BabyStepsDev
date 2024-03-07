package microservice.micropagamentos.adapter.mapper;

import microservice.micropagamentos.adapter.in.consumer.dto.HistoryDtoRequest;
import microservice.micropagamentos.adapter.in.consumer.dto.InscritoDtoRequest;
import microservice.micropagamentos.adapter.in.consumer.event.SagaEventRequest;
import microservice.micropagamentos.application.core.domain.History;
import microservice.micropagamentos.application.core.domain.Inscrito;
import microservice.micropagamentos.application.core.domain.SagaEvent;
import microservice.micropagamentos.application.core.domain.enums.ESagaStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MapperIn {

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
        if (status == null) {
            return null;
        }
        return ESagaStatus.fromValue(status);
    }

}

