package microservice.micronoticias.adapter.in.mapper;

import microservice.micronoticias.adapter.in.dto.request.EditoriaCriarDtoIn;
import microservice.micronoticias.adapter.in.dto.request.EditoriaDtoIn;
import microservice.micronoticias.adapter.in.dto.request.EditoriaUpdateDtoIn;
import microservice.micronoticias.adapter.in.dto.response.EditoriaCriarDtoOut;
import microservice.micronoticias.adapter.in.dto.response.EditoriaListarDtoOut;
import microservice.micronoticias.adapter.in.dto.response.EditoriaUpdateDtoOut;
import microservice.micronoticias.application.core.domain.Editoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditoriaMapperIn {

    Editoria toEditoria(EditoriaDtoIn editoriaDtoIn);

    Editoria toEditoria(EditoriaCriarDtoIn editoriaCriarDtoIn);

    Editoria toEditoria(EditoriaUpdateDtoIn editoriaUpdateDtoIn);

    EditoriaCriarDtoOut toEditoriaCriarDtoOut(Editoria editoria);

    EditoriaListarDtoOut toEditoriaListarDtoOut(Editoria editoria);

    EditoriaUpdateDtoOut toEditoriaUpdateDtoOut(Editoria editoria);
}

