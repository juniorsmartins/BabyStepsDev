package microservice.microinscricoes.adapter.mapper;

import microservice.microinscricoes.adapter.in.controller.dto.request.FiltersDtoEvent;
import microservice.microinscricoes.adapter.in.controller.dto.request.InscricaoFiltroDto;
import microservice.microinscricoes.adapter.out.producer.dto.HistoryDtoRequest;
import microservice.microinscricoes.adapter.out.producer.dto.SagaEventRequest;
import microservice.microinscricoes.adapter.out.repository.entity.*;
import microservice.microinscricoes.adapter.out.repository.entity.value_object.HistoryDb;
import microservice.microinscricoes.application.core.domain.*;
import microservice.microinscricoes.application.core.domain.filtro.FiltersEvent;
import microservice.microinscricoes.application.core.domain.filtro.InscricaoFiltro;
import microservice.microinscricoes.application.core.domain.value_object.History;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOut {

    InscricaoEntity toInscricaoEntity(Inscricao inscricao);

    Inscricao toInscricao(InscricaoEntity inscricaoEntity);

    TorneioEntity toTorneioEntity(Torneio torneio);

    Torneio toTorneio(TorneioEntity torneioEntity);

    TimeEntity toTimeEntity(Time time);

    Time toTime(TimeEntity timeEntity);

    InscricaoFiltroDto toInscricaoFiltroDto(InscricaoFiltro inscricaoFiltro);

    FiltersDtoEvent toFiltersDtoEvent(FiltersEvent filtersEvent);

    SagaEventEntity toSagaEventEntity(SagaEvent sagaEvent);

    SagaEvent toSagaEvent(SagaEventEntity sagaEventEntity);

    SagaEventRequest toSagaEventRequest(SagaEvent sagaEvent);

    HistoryDb toHistoryDb(History history);

    History toHistory(HistoryDb historyDb);

    HistoryDtoRequest toHistoryDtoRequest(History history);

    InscritoEntity toInscritoEntity(Inscrito inscrito);

    Inscrito toInscrito(InscritoEntity inscritoEntity);
}

