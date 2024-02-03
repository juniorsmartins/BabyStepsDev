package microservice.microinscricoes.adapter.out.repository;

import microservice.microinscricoes.adapter.out.repository.entity.InscritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscritoRepository extends JpaRepository<InscritoEntity, Long> { }

