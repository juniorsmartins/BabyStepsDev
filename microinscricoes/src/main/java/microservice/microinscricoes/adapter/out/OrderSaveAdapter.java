package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.mapper.MapperOut;
import microservice.microinscricoes.adapter.out.repository.OrderRepository;
import microservice.microinscricoes.application.core.domain.Order;
import microservice.microinscricoes.application.port.output.OrderSaveOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderSaveAdapter implements OrderSaveOutputPort {

    private final OrderRepository orderRepository;

    private final MapperOut mapperOut;

    @Transactional
    @Override
    public Order save(Order order) {

        log.info("Iniciado adaptador para salvar Order.");

        var orderSaved = Optional.ofNullable(order)
            .map(this.mapperOut::toOrderEntity)
            .map(this.orderRepository::save)
            .map(this.mapperOut::toOrder)
            .orElseThrow();

        log.info("Finalizado adaptador para salvar Order, com Id: {}.", orderSaved.getId());

        return orderSaved;
    }
}

