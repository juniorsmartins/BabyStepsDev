package microservice.micronoticias.adapter.out.mapper;

import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.application.core.domain.Noticia;
import org.springframework.stereotype.Service;

@Service
public class NoticiaMapperOutImpl implements NoticiaMapperOut {

    @Override
    public NoticiaEntity toNoticiaEntity(Noticia noticia) {
        return NoticiaEntity.builder()
                .chapeu(noticia.getChapeu())
                .titulo(noticia.getTitulo())
                .linhaFina(noticia.getLinhaFina())
                .lide(noticia.getLide())
                .corpo(noticia.getCorpo())
                .autorias(noticia.getAutorias())
                .fontes(noticia.getFontes())
                .build();
    }

    @Override
    public Noticia toNoticia(NoticiaEntity entity) {

        var noticia = new Noticia();
        noticia.setId(entity.getId());
        noticia.setChapeu(entity.getChapeu());
        noticia.setTitulo(entity.getTitulo());
        noticia.setLinhaFina(entity.getLinhaFina());
        noticia.setLide(entity.getLide());
        noticia.setCorpo(entity.getCorpo());
        noticia.setAutorias(entity.getAutorias());
        noticia.setFontes(entity.getFontes());

        return noticia;
    }
}
