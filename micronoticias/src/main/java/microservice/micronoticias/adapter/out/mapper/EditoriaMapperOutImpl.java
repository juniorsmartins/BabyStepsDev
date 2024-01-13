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
//        return EditoriaEntity.builder()
//            .id(editoria.getId())
//            .nomenclatura(editoria.getNomenclatura())
//            .descricao(editoria.getDescricao())
//            .build();
    }

    @Override
    public Editoria toEditoria(EditoriaEntity editoriaEntity) {

        var editoria = new Editoria();
        BeanUtils.copyProperties(editoriaEntity, editoria);

//        var editoria = new Editoria();
//        editoria.setId(editoriaEntity.getId());
//        editoria.setNomenclatura(editoriaEntity.getNomenclatura());
//        editoria.setDescricao(editoriaEntity.getDescricao());

        return editoria;
    }
}
