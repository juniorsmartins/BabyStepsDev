package microservice.microinscricoes.adapter.out.repository;

import microservice.microinscricoes.adapter.out.repository.entity.InscricaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<InscricaoEntity, Long>,
        JpaSpecificationExecutor<InscricaoEntity> { }

