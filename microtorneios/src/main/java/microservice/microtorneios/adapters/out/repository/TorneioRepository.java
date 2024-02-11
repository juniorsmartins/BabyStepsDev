package microservice.microtorneios.adapters.out.repository;

import microservice.microtorneios.adapters.out.repository.entity.TorneioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TorneioRepository extends JpaRepository<TorneioEntity, Long> {

    boolean existsById(Long id);
}

