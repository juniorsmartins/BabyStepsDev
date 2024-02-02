package microservice.microinscricoes.adapter.out.mapper;

import microservice.microinscricoes.adapter.out.entity.InscricaoEntity;
import microservice.microinscricoes.application.core.domain.Inscricao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InscricaoMapperOut {

    InscricaoEntity toInscricaoEntity(Inscricao inscricao);

    Inscricao toInscricao(InscricaoEntity inscricaoEntity);
}

