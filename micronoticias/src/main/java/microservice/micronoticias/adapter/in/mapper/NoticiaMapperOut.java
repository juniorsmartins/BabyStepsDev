package microservice.micronoticias.adapter.in.mapper;

import microservice.micronoticias.adapter.in.dto.response.NoticiaCadastrarDtoOut;
import microservice.micronoticias.application.core.domain.Noticia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaMapperOut {

    NoticiaCadastrarDtoOut toNoticiaCadastrarDtoOut(Noticia noticia);
}

