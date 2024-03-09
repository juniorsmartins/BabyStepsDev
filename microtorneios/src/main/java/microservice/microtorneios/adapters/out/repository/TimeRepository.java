package microservice.microtorneios.adapters.out.repository;

import microservice.microtorneios.adapters.out.repository.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<TimeEntity, Long> { }

