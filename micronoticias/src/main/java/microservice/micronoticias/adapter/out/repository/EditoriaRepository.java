package microservice.micronoticias.adapter.out.repository;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.application.core.domain.Editoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditoriaRepository extends JpaRepository<EditoriaEntity, Long> {

    Optional<EditoriaEntity> findByNomenclatura(String nomenclatura);
}

