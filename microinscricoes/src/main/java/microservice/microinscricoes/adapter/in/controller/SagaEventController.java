package microservice.microinscricoes.adapter.in.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.in.controller.dto.request.FiltersDtoEvent;
import microservice.microinscricoes.adapter.in.controller.dto.response.SagaEventResponse;
import microservice.microinscricoes.adapter.mapper.MapperIn;
import microservice.microinscricoes.application.port.input.SagaEventFindAllInputPort;
import microservice.microinscricoes.application.port.input.SagaEventFindByFiltersInputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/eventos")
@RequiredArgsConstructor
public class SagaEventController {

    private static final String APPLICATION_YAML_VALUE = "application/x-yaml";

    private final SagaEventFindByFiltersInputPort sagaEventFindByFiltersInputPort;

    private final SagaEventFindAllInputPort sagaEventFindAllInputPort;

    private final MapperIn mapperIn;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public ResponseEntity<Page<SagaEventResponse>> findByFilters(FiltersDtoEvent filtersDtoEvent,
        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5) final Pageable paginacao) {

        log.info("Requisição recebida para buscar SagaEvent por filtro.");

        var response = Optional.ofNullable(filtersDtoEvent)
            .map(this.mapperIn::toFiltersEvent)
            .map(filtro -> this.sagaEventFindByFiltersInputPort.findByFilters(filtro, paginacao))
            .map(page -> page.map(this.mapperIn::toSagaEventResponse))
            .orElseThrow();

        log.info("Requisição concluída para buscar SagaEvent {} por filtro {}.", response, filtersDtoEvent);

        return ResponseEntity
            .ok()
            .body(response);
    }

    @GetMapping(path = {"/all"})
    public ResponseEntity<List<SagaEventResponse>> findAll() {

        log.info("Requisição recebida para buscar lista de SagaEvent.");

        var responseList = this.sagaEventFindAllInputPort.findAllByCreatedAtDesc()
            .stream()
            .map(this.mapperIn::toSagaEventResponse)
            .toList();

        log.info("Requisição concluída para buscar lista de SagaEvent, {}.", responseList);

        return ResponseEntity
            .ok()
            .body(responseList);
    }
}

