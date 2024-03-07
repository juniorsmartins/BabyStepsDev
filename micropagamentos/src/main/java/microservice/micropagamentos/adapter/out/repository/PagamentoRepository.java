package microservice.micropagamentos.adapter.out.repository;

import microservice.micropagamentos.adapter.out.repository.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

    Boolean existsBySagaEventIdAndTransactionId(Long sagaEventId, String transactionId);

    Optional<PagamentoEntity> findBySagaEventIdAndTransactionId(Long sagaEventId, String transactionId);
}

