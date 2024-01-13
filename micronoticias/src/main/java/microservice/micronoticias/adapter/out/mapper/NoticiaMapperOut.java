package microservice.micronoticias.adapter.out.mapper;

import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.application.core.domain.Noticia;

public interface NoticiaMapperOut {

    NoticiaEntity toNoticiaEntity(Noticia noticia);

    Noticia toNoticia(NoticiaEntity noticiaEntity);
}

