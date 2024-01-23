package microservice.micronoticias.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.application.port.output.EditoriaInUseOutputPort;
import microservice.micronoticias.config.exception.http_404.EditoriaNotFoundException;
import microservice.micronoticias.config.exception.http_500.ProhibitedNullValueException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EditoriaInUseAdapter implements EditoriaInUseOutputPort {

    private final EditoriaRepository editoriaRepository;

    @Transactional(readOnly = true)
    @Override
    public Boolean inUse(final Long id) {

        log.info("Iniciado adaptador para verificar Editoria em uso.");

        var response = !Optional.ofNullable(id)
            .map(this::searchEditor)
            .orElseThrow(ProhibitedNullValueException::new)
            .getNoticias().isEmpty();

        log.info("Finalizado adaptador para verificar Editoria em uso, com Id: {}.", id);

        return response;
    }

    private EditoriaEntity searchEditor(final Long id) {
        return this.editoriaRepository.findById(id)
            .orElseThrow(() -> new EditoriaNotFoundException(id));
    }
}

