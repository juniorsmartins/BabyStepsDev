package microservice.microtimes.adapter.out.repository;

import microservice.microtimes.adapter.out.repository.entity.ValidationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidationRepository extends JpaRepository<ValidationEntity, Long> {

    Boolean existsBySagaEventIdAndTransactionId(Long sagaEventId, String transactionId);

    Optional<ValidationEntity> findBySagaEventIdAndTransactionId(Long sagaEventId, String transactionId);
}

