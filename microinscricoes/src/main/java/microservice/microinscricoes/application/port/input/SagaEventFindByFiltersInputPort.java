package microservice.microinscricoes.application.port.input;

import microservice.microinscricoes.application.core.domain.SagaEvent;
import microservice.microinscricoes.application.core.domain.filtro.FiltersEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SagaEventFindByFiltersInputPort {

    Page<SagaEvent> findByFilters(FiltersEvent filtersEvent, Pageable paginacao);
}

