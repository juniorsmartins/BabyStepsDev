package microservice.micropagamentos.adapter.out.repository;

import microservice.micropagamentos.adapter.out.repository.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

    Boolean existsByTorneioIdAndTimeId(Long torneioId, Long timeId);

    Optional<PagamentoEntity> findByTorneioIdAndTimeId(Long torneioId, Long timeId);

}

