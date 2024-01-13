package microservice.micronoticias.adapter.out.mapper;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.application.core.domain.Editoria;

public interface EditoriaMapperOut {

    EditoriaEntity toEditoriaEntity(Editoria editoria);

    Editoria toEditoria(EditoriaEntity editoriaEntity);
}

