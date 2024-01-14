package microservice.micronoticias.adapter.out.mapper;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.config.exception.http_500.EditoriaMapperOutImplException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditoriaMapperOutImpl implements EditoriaMapperOut {

    @Override
    public EditoriaEntity toEditoriaEntity(Editoria editoria) {

        return Optional.ofNullable(editoria)
            .map(edit -> {
                var editoriaEntity = new EditoriaEntity();
                BeanUtils.copyProperties(edit, editoriaEntity);
                return editoriaEntity;
            })
            .orElseThrow(EditoriaMapperOutImplException::new);
    }

    @Override
    public Editoria toEditoria(EditoriaEntity editoriaEntity) {

        return Optional.ofNullable(editoriaEntity)
            .map(edit -> {
                var editoria = new Editoria();
                BeanUtils.copyProperties(edit, editoria);
                return editoria;
            })
            .orElseThrow(EditoriaMapperOutImplException::new);
    }
}
