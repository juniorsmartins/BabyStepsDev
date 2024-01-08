package microservice.micronoticias.adapter;

import lombok.RequiredArgsConstructor;
import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.adapter.out.mapper.NoticiaMapperOut;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.port.output.NoticiaSalvarOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NoticiaSalvarAdapter implements NoticiaSalvarOutputPort {

    private final NoticiaRepository repository;

    private final NoticiaMapperOut mapperOut;

    @Transactional
    @Override
    public Noticia salvar(Noticia noticia) {

//        return Optional.of(noticia)
//                .map(this.mapperOut::toNoticia)
//                .map(this.repository::save)
//                .map(this.mapperOut::toNoticiaEntity)
//                .orElseThrow();

        return Optional.of(noticia)
            .map(this::toNoticiaEntity)
            .map(this.repository::save)
            .map(this::toNoticia)
            .orElseThrow();
    }

    private NoticiaEntity toNoticiaEntity(Noticia noticia) {

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

    private Noticia toNoticia(NoticiaEntity entity) {

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