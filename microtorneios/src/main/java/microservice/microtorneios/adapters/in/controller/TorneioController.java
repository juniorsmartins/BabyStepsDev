package microservice.microtorneios.adapters.in.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.in.dto.request.TorneioCreateDtoRequest;
import microservice.microtorneios.adapters.in.dto.response.TorneioCreateDtoResponse;
import microservice.microtorneios.adapters.in.mapper.TorneioMapperIn;
import microservice.microtorneios.application.port.input.TorneioCreateInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/torneios")
@RequiredArgsConstructor
public class TorneioController {

    private final TorneioCreateInputPort torneioCreateInputPort;

    private final TorneioMapperIn torneioMapperIn;

    @PostMapping
    public ResponseEntity<TorneioCreateDtoResponse> create(@RequestBody @Valid TorneioCreateDtoRequest torneioCreateDtoRequest) {

        var response = Optional.of(torneioCreateDtoRequest)
            .map(this.torneioMapperIn::toTorneio)
            .map(this.torneioCreateInputPort::create)
            .map(this.torneioMapperIn::toTorneioCreateDtoResponse)
            .orElseThrow();

        return ResponseEntity
            .created(URI.create("/api/v1/torneios" + response.id()))
            .body(response);
    }
}

