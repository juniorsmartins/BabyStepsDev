package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.mapper.MapperOut;
import microservice.microinscricoes.adapter.out.repository.SagaEventRepository;
import microservice.microinscricoes.application.core.domain.SagaEvent;
import microservice.microinscricoes.application.port.output.SagaEventFindAllOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public final class SagaEventFindAllAdapter implements SagaEventFindAllOutputPort {

    private final SagaEventRepository sagaEventRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public List<SagaEvent> findAllByCreatedAtDesc() {

        log.info("Iniciado adaptador para buscar lista de SagaEvent.");

        var listaEventos = this.sagaEventRepository.findAllByCreatedAtDesc()
            .stream()
            .map(this.mapperOut::toSagaEvent)
            .toList();

        log.info("Finalizado adaptador para buscar lista de SagaEvent, {}.", listaEventos);

        return listaEventos;
    }
}

