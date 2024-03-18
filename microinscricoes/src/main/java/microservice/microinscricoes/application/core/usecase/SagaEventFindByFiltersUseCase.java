package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.SagaEvent;
import microservice.microinscricoes.application.core.domain.filtro.FiltersEvent;
import microservice.microinscricoes.application.port.input.SagaEventFindByFiltersInputPort;
import microservice.microinscricoes.application.port.output.SagaEventFindByFiltersOutputPort;
import microservice.microinscricoes.config.exception.http_409.FiltersEventEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

public class SagaEventFindByFiltersUseCase implements SagaEventFindByFiltersInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventFindByFiltersUseCase.class);

    private final SagaEventFindByFiltersOutputPort sagaEventFindByFiltersOutputPort;

    public SagaEventFindByFiltersUseCase(SagaEventFindByFiltersOutputPort sagaEventFindByFiltersOutputPort) {
        this.sagaEventFindByFiltersOutputPort = sagaEventFindByFiltersOutputPort;
    }

    @Override
    public Page<SagaEvent> findByFilters(FiltersEvent filtersEvent, Pageable paginacao) {

        log.info("Iniciado serviço para buscar SagaEvent por filters.");

        var event = Optional.ofNullable(filtersEvent)
            .map(this::validateEmptyFilters)
            .map(filtro -> this.sagaEventFindByFiltersOutputPort.findByFilters(filtro, paginacao))
            .orElseThrow();

        log.info("Finalizado serviço para buscar SagaEvent {} por filters {}.", event, filtersEvent);

        return event;
    }

    private FiltersEvent validateEmptyFilters(FiltersEvent filtersEvent) {
        if (ObjectUtils.isEmpty(filtersEvent.getId()) && ObjectUtils.isEmpty(filtersEvent.getTransactionId())) {
            throw new FiltersEventEmptyException();
        }
        return filtersEvent;
    }

}

