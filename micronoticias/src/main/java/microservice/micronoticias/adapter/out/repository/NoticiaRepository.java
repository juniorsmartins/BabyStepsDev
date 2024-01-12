package microservice.micronoticias.adapter.out.repository;

import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticiaRepository extends JpaRepository<NoticiaEntity, Long> {

    Optional<NoticiaEntity> findByTitulo(String titulo);
}

