package microservice.microtorneios.adapters.in.mapper;

import microservice.microtorneios.adapters.in.dto.request.TimeInventoryDtoId;
import microservice.microtorneios.adapters.in.dto.response.TimeInventoryCreateDtoResponse;
import microservice.microtorneios.application.core.domain.TimeInventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimeInventoryInMapper {

    @Mapping(target = "nomeFantasia", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "status", ignore = true)
    TimeInventory toTimeInventory(TimeInventoryDtoId timeInventoryDtoId);

    TimeInventoryCreateDtoResponse toTimeInventoryCreateDtoResponse(TimeInventory timeInventory);
}

