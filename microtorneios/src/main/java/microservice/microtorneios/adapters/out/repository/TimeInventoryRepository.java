package microservice.microtorneios.adapters.out.repository;

import microservice.microtorneios.adapters.out.repository.entity.TimeInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeInventoryRepository extends JpaRepository<TimeInventoryEntity, Long> { }

