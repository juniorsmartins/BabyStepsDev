package microservice.micronoticias.adapter.in.mapper;

import microservice.micronoticias.adapter.in.dto.request.EditoriaCriarDtoIn;
import microservice.micronoticias.adapter.in.dto.response.EditoriaCriarDtoOut;
import microservice.micronoticias.application.core.domain.Editoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditoriaMapperIn {

    Editoria toEditoria(EditoriaCriarDtoIn editoriaCriarDtoIn);

    EditoriaCriarDtoOut toEditoriaCriarDtoOut(Editoria editoria);
}

