package microservice.microtorneios.adapters.out.repository;

import microservice.microtorneios.adapters.out.repository.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeInventoryRepository extends JpaRepository<TimeEntity, Long> { }

