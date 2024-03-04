package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.SagaEvent;
import microservice.microinscricoes.application.core.domain.filtro.FiltersEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SagaEventFindByFiltersOutputPort {

    Page<SagaEvent> findByFilters(FiltersEvent filtersEvent, Pageable paginacao);
}

