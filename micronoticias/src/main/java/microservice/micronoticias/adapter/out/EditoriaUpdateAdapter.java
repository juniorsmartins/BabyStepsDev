package microservice.micronoticias.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.mapper.EditoriaMapperOut;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.port.output.EditoriaUpdateOutputPort;
import microservice.micronoticias.config.exception.http_404.EditoriaNotFoundException;
import microservice.micronoticias.config.exception.http_500.FailedToUpdateEditorException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EditoriaUpdateAdapter implements EditoriaUpdateOutputPort {

    private final EditoriaRepository editoriaRepository;

    private final EditoriaMapperOut mapperOut;

    @Transactional
    @Override
    public Editoria update(final Editoria editoria) {

        log.info("Iniciado adaptador para atualizar Editoria.");

        var editoriaAtual = Optional.ofNullable(editoria.getId())
            .map(this::searchEditor)
            .map(entity -> this.overrideValues(entity, editoria))
            .map(this.mapperOut::toEditoria)
            .orElseThrow(FailedToUpdateEditorException::new);

        log.info("Finalizado adaptador para atualizar Editoria por Id: {}.", editoriaAtual.getId());

        return editoriaAtual;
    }

    private EditoriaEntity searchEditor(final Long id) {
        return this.editoriaRepository.findById(id)
            .orElseThrow(() -> new EditoriaNotFoundException(id));
    }

    private EditoriaEntity overrideValues(EditoriaEntity editoriaEntity, final Editoria editoria) {
        BeanUtils.copyProperties(editoria, editoriaEntity, "id");
        return editoriaEntity;
    }
}

