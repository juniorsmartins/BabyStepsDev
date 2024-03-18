package microservice.micropagamentos.adapter.mapper;

import microservice.micropagamentos.adapter.in.consumer.dto.HistoryDtoRequest;
import microservice.micropagamentos.adapter.in.consumer.dto.InscritoDtoRequest;
import microservice.micropagamentos.adapter.in.consumer.event.SagaEventRequest;
import microservice.micropagamentos.application.core.domain.History;
import microservice.micropagamentos.application.core.domain.Inscrito;
import microservice.micropagamentos.application.core.domain.SagaEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperIn {

    @Mapping(source = "id", target = "sagaEventId")
    SagaEvent toSagaEvent(SagaEventRequest sagaEventRequest);

    @Mapping(source = "sagaEventId", target = "id")
    SagaEventRequest toSagaEventRequest(SagaEvent sagaEvent);

    @Mapping(source = "inscricao.id", target = "inscricaoId")
    Inscrito toInscrito(InscritoDtoRequest inscritoDtoRequest);

    History toHistory(HistoryDtoRequest historyDtoRequest);

}

