package microservice.microtimes.adapter.in.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtimes.adapter.in.dto.request.TimeCreateDtoRequest;
import microservice.microtimes.adapter.in.dto.response.TimeCreateDtoResponse;
import microservice.microtimes.adapter.in.mapper.MapperIn;
import microservice.microtimes.application.port.input.TimeCreateInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/times")
@RequiredArgsConstructor
public class TimeController {

    private final TimeCreateInputPort timeCreateInputPort;

    private final MapperIn mapperIn;

    @PostMapping
    public ResponseEntity<TimeCreateDtoResponse> create(@RequestBody @Valid TimeCreateDtoRequest timeCreateDtoRequest) {

        log.info("Recebida requisição para criar Time.");

        var response = Optional.of(timeCreateDtoRequest)
            .map(this.mapperIn::toTime)
            .map(this.timeCreateInputPort::create)
            .map(this.mapperIn::toTimeCreateDtoResponse)
            .orElseThrow();

        log.info("Time criado com sucesso, com nome fantasia: {}", response.nomeFantasia());

        return ResponseEntity
            .created(URI.create("/api/v1/times" + response.id()))
            .body(response);
    }
}

