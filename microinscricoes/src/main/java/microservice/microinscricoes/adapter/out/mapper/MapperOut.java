package microservice.microinscricoes.adapter.out.mapper;

import microservice.microinscricoes.adapter.in.controller.dto.request.InscricaoFiltroDto;
import microservice.microinscricoes.adapter.out.repository.entity.InscricaoEntity;
import microservice.microinscricoes.adapter.out.repository.entity.InscritoEntity;
import microservice.microinscricoes.adapter.out.repository.entity.TimeEntity;
import microservice.microinscricoes.adapter.out.repository.entity.TorneioEntity;
import microservice.microinscricoes.application.core.domain.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOut {

    InscricaoEntity toInscricaoEntity(Inscricao inscricao);

    Inscricao toInscricao(InscricaoEntity inscricaoEntity);

    InscritoEntity toInscritoEntity(Inscrito inscrito);

    Inscrito toInscrito(InscritoEntity inscritoEntity);

    TorneioEntity toTorneioEntity(Torneio torneio);

    Torneio toTorneio(TorneioEntity torneioEntity);

    TimeEntity toTimeEntity(Time time);

    Time toTime(TimeEntity timeEntity);

    InscricaoFiltroDto toInscricaoFiltroDto(InscricaoFiltro inscricaoFiltro);
}

