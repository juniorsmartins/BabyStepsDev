package microservice.microinscricoes.adapter.mapper;

import microservice.microinscricoes.adapter.in.controller.dto.request.InscricaoFiltroDto;
import microservice.microinscricoes.adapter.out.producer.dto.HistoryDtoRequest;
import microservice.microinscricoes.adapter.out.producer.dto.OrderDtoRequest;
import microservice.microinscricoes.adapter.out.producer.dto.SagaEventRequest;
import microservice.microinscricoes.adapter.out.repository.entity.*;
import microservice.microinscricoes.adapter.out.repository.entity.value_object.HistoryDb;
import microservice.microinscricoes.application.core.domain.*;
import microservice.microinscricoes.application.core.domain.filtro.InscricaoFiltro;
import microservice.microinscricoes.application.core.domain.value_object.History;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperOut {

    InscricaoEntity toInscricaoEntity(Inscricao inscricao);

    Inscricao toInscricao(InscricaoEntity inscricaoEntity);

    TorneioEntity toTorneioEntity(Torneio torneio);

    Torneio toTorneio(TorneioEntity torneioEntity);

    TimeEntity toTimeEntity(Time time);

    Time toTime(TimeEntity timeEntity);

    InscricaoFiltroDto toInscricaoFiltroDto(InscricaoFiltro inscricaoFiltro);

    SagaEventEntity toSagaEventEntity(SagaEvent sagaEvent);

    SagaEvent toSagaEvent(SagaEventEntity sagaEventEntity);

    SagaEventRequest toSagaEventRequest(SagaEvent sagaEvent);

    OrderEntity toOrderEntity(Order order);

    Order toOrder(OrderEntity orderEntity);

    @Mapping(source = "inscrito.numeroBanco", target = "numeroBanco")
    @Mapping(source = "inscrito.numeroAgencia", target = "numeroAgencia")
    @Mapping(source = "inscrito.numeroCartao", target = "numeroCartao")
    @Mapping(source = "inscrito.tipo", target = "tipo")
    OrderDtoRequest toOrderDtoRequest(Order order);

    HistoryDb toHistoryDb(History history);

    History toHistory(HistoryDb historyDb);

    HistoryDtoRequest toHistoryDtoRequest(History history);

    InscritoEntity toInscritoEntity(Inscrito inscrito);

    Inscrito toInscrito(InscritoEntity inscritoEntity);
}

