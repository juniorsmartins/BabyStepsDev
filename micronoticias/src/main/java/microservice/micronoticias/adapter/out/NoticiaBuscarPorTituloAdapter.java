package microservice.micronoticias.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.mapper.NoticiaMapperOut;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.port.output.NoticiaBuscarPorTituloOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NoticiaBuscarPorTituloAdapter implements NoticiaBuscarPorTituloOutputPort {

    private final NoticiaRepository noticiaRepository;

    private final NoticiaMapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Optional<Noticia> buscarPorTitulo(String titulo) {

        log.info("Iniciado adaptador para buscar Noticia.");

        var noticiaEncontrada = this.noticiaRepository.findByTitulo(titulo);
        if (noticiaEncontrada.isEmpty()) {
            return Optional.empty();
        }

        var noticia = this.mapperOut.toNoticia(noticiaEncontrada.get());

        log.info("Finalizado adaptador para buscar Notícia por Título: {}", titulo);

        return Optional.of(noticia);
    }
}

