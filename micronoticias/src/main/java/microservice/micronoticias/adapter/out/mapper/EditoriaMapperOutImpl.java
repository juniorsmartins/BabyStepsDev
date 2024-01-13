package microservice.micronoticias.adapter.out.mapper;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.application.core.domain.Editoria;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EditoriaMapperOutImpl implements EditoriaMapperOut {

    @Override
    public EditoriaEntity toEditoriaEntity(Editoria editoria) {

        var editoriaEntity = new EditoriaEntity();
        BeanUtils.copyProperties(editoria, editoriaEntity);
        return editoriaEntity;
    }

    @Override
    public Editoria toEditoria(EditoriaEntity editoriaEntity) {

        var editoria = new Editoria();
        BeanUtils.copyProperties(editoriaEntity, editoria);
        return editoria;
    }
}
