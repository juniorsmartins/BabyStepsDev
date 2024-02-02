package microservice.microinscricoes.adapter.in.mapper;

import microservice.microinscricoes.adapter.in.dto.request.InscricaoOpenDtoIn;
import microservice.microinscricoes.adapter.in.dto.response.InscricaoOpenDtoOut;
import microservice.microinscricoes.application.core.domain.Inscricao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InscricaoMapperIn {

    Inscricao toInscricao(InscricaoOpenDtoIn inscricaoOpenDtoIn);

    InscricaoOpenDtoOut toInscricaoOpenDtoOut(Inscricao inscricao);
}

