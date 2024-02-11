package microservice.microinscricoes.adapter.out.repository;

import microservice.microinscricoes.adapter.out.repository.entity.TorneioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TorneioRepository extends JpaRepository<TorneioEntity, Long> { }

