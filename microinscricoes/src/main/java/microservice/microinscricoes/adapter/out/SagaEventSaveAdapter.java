package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.mapper.MapperOut;
import microservice.microinscricoes.adapter.out.repository.OrderRepository;
import microservice.microinscricoes.adapter.out.repository.SagaEventRepository;
import microservice.microinscricoes.application.core.domain.SagaEvent;
import microservice.microinscricoes.application.port.output.SagaEventSaveOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SagaEventSaveAdapter implements SagaEventSaveOutputPort {

    private final SagaEventRepository sagaEventRepository;

    private final OrderRepository orderRepository;

    private final MapperOut mapperOut;

    @Transactional
    @Override
    public SagaEvent save(SagaEvent sagaEvent) {

        log.info("Iniciado adaptador para registrar Inscrito.");

        var eventSaved = Optional.ofNullable(sagaEvent)
            .map(this.mapperOut::toSagaEventEntity)
            .map(this.sagaEventRepository::save)
            .map(this.mapperOut::toSagaEvent)
            .orElseThrow();

        log.info("Finalizado adaptador para registrar Inscrito, com Id: {}.", eventSaved.getId());

        return eventSaved;
    }
}

