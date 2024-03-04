package microservice.microinscricoes.adapter.out.repository;

import microservice.microinscricoes.adapter.out.repository.entity.SagaEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SagaEventRepository extends JpaRepository<SagaEventEntity, Long>,
        JpaSpecificationExecutor<SagaEventEntity> {

    List<SagaEventEntity> findAllByCreatedAtDesc();
}

