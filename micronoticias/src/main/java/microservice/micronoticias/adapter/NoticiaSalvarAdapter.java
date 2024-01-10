package microservice.micronoticias.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.mapper.NoticiaMapperOut;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.port.output.NoticiaSalvarOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NoticiaSalvarAdapter implements NoticiaSalvarOutputPort {

    private final NoticiaRepository repository;

    private final NoticiaMapperOut mapperOut;

    @Transactional
    @Override
    public Noticia salvar(Noticia noticia) {

        log.info("Iniciado adaptador para salvar Notícia, com título: {}.", noticia.getTitulo());

        var noticiaSalva = Optional.of(noticia)
            .map(this.mapperOut::toNoticiaEntity)
            .map(this.repository::save)
            .map(this.mapperOut::toNoticia)
            .orElseThrow();

        log.info("Finalizado adaptador para salvar Notícia, com título: {}.", noticiaSalva.getTitulo());

        return noticiaSalva;
    }
}