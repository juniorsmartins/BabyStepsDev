package microservice.microinscricoes.adapter.out.repository;

import microservice.microinscricoes.adapter.out.repository.entity.value_object.HistoryDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryDb, Long> { }

