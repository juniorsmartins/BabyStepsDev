package microservice.micronoticias.adapter.in.mapper;

import microservice.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import microservice.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import microservice.micronoticias.application.core.domain.Noticia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaMapperIn {

    Noticia toNoticia(NoticiaCriarDtoIn noticiaCriarDtoIn);

    NoticiaCriarDtoOut toNoticiaCadastrarDtoOut(Noticia noticia);
}

