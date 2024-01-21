package microservice.micronoticias.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.mapper.EditoriaMapperOut;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.port.output.EditoriaUpdateOutputPort;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

        var editoriaSalva = this.editoriaRepository.findById(editoria.getId())
            .map(entity -> this.overrideValues(entity, editoria))
            .map(this.mapperOut::toEditoria)
            .orElseThrow();

        log.info("Finalizado adaptador para atualizar Editoria por Id: {}.", editoriaSalva.getId());

        return editoriaSalva;
    }

    private EditoriaEntity overrideValues(EditoriaEntity editoriaEntity, final Editoria editoria) {
        BeanUtils.copyProperties(editoria, editoriaEntity, "id");
        return editoriaEntity;
    }
}

