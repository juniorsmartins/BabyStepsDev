package microservice.micronoticias.adapter;

import lombok.RequiredArgsConstructor;
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

        return Optional.of(noticia)
            .map(this.mapperOut::toNoticiaEntity)
            .map(this.repository::save)
            .map(this.mapperOut::toNoticia)
            .orElseThrow();
    }
}