package microservice.microinscricoes.adapter.out.repository;

import microservice.microinscricoes.adapter.out.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> { }

