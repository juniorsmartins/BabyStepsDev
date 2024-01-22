package microservice.micronoticias.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.port.output.NoticiaDeleteOutputPort;
import microservice.micronoticias.config.exception.http_404.NoticiaNotFoundException;
import microservice.micronoticias.config.exception.http_500.ProhibitedNullValueException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NoticiaDeleteAdapter implements NoticiaDeleteOutputPort {

    private final NoticiaRepository noticiaRepository;

    @Transactional
    @Override
    public void deleteById(final Long id) {

        log.info("Iniciado adaptador para deletar Notícia por Id.");

        Optional.ofNullable(id)
            .map(this.noticiaRepository::findById)
            .orElseThrow(ProhibitedNullValueException::new)
            .ifPresentOrElse(this.noticiaRepository::delete,
                () -> {throw new NoticiaNotFoundException(id);}
            );

        log.info("Finalizado adaptador para deletar Notícia por Id: {}.", id);
    }
}

