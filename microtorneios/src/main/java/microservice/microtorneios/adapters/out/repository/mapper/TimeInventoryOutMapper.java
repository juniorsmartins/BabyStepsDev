package microservice.microtorneios.adapters.out.repository.mapper;

import microservice.microtorneios.adapters.out.repository.entity.TimeInventoryEntity;
import microservice.microtorneios.application.core.domain.TimeInventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeInventoryOutMapper {

    TimeInventoryEntity toTimeInventoryEntity(TimeInventory timeInventory);
}

