package microservice.micronoticias.adapter.out.mapper;

import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.application.core.domain.Noticia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticiaMapperOut {

    NoticiaEntity toNoticia(Noticia noticia);

    Noticia toNoticiaEntity(NoticiaEntity noticiaEntity);
}

