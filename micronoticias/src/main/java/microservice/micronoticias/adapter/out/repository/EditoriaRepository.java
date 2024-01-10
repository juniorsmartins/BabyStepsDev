package microservice.micronoticias.adapter.out.repository;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoriaRepository extends JpaRepository<EditoriaEntity, Long> { }

