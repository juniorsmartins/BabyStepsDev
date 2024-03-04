package microservice.microinscricoes.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.mapper.MapperOut;
import microservice.microinscricoes.adapter.out.repository.SagaEventRepository;
import microservice.microinscricoes.adapter.out.specs.SagaEventSpecification;
import microservice.microinscricoes.application.core.domain.SagaEvent;
import microservice.microinscricoes.application.core.domain.filtro.FiltersEvent;
import microservice.microinscricoes.application.port.output.SagaEventFindByFiltersOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SagaEventFindByFiltersAdapter implements SagaEventFindByFiltersOutputPort {

    private final SagaEventRepository sagaEventRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Page<SagaEvent> findByFilters(FiltersEvent filtersEvent, Pageable paginacao) {

        log.info("Iniciado adaptador para buscar SagaEvent por filters.");

        var sagaEncontrada = Optional.ofNullable(filtersEvent)
            .map(this.mapperOut::toFiltersDtoEvent)
            .map(parametros -> this.sagaEventRepository.findAll(SagaEventSpecification.consultarDinamicamente(parametros), paginacao))
            .map(page -> page.map(this.mapperOut::toSagaEvent))
            .orElseThrow();

        log.info("Finalizado adaptador para buscar SagaEvent {} por filters {}.", sagaEncontrada, filtersEvent);

        return sagaEncontrada;
    }
}

