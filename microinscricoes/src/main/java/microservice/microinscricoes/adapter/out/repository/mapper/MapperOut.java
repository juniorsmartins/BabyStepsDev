package microservice.microinscricoes.adapter.out.repository.mapper;

import microservice.microinscricoes.adapter.out.repository.entity.InscricaoEntity;
import microservice.microinscricoes.adapter.out.repository.entity.InscritoEntity;
import microservice.microinscricoes.adapter.out.repository.entity.TorneioEntity;
import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.core.domain.Inscrito;
import microservice.microinscricoes.application.core.domain.Torneio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOut {

    InscricaoEntity toInscricaoEntity(Inscricao inscricao);

    Inscricao toInscricao(InscricaoEntity inscricaoEntity);

    InscritoEntity toInscritoEntity(Inscrito inscrito);

    Inscrito toInscrito(InscritoEntity inscritoEntity);

    TorneioEntity toTorneioEntity(Torneio torneio);

    Torneio toTorneio(TorneioEntity torneioEntity);
}

