package microservice.microtorneios.adapters.in.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.in.dto.request.TorneioCreateDtoRequest;
import microservice.microtorneios.adapters.in.dto.response.TorneioCreateDtoResponse;
import microservice.microtorneios.adapters.in.mapper.TorneioInMapper;
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

    private final TorneioInMapper torneioInMapper;

    @PostMapping
    public ResponseEntity<TorneioCreateDtoResponse> create(@RequestBody @Valid TorneioCreateDtoRequest torneioCreateDtoRequest) {

        var response = Optional.of(torneioCreateDtoRequest)
            .map(this.torneioInMapper::toTorneio)
            .map(torneio -> {
                System.out.println("\n\n ----- 1 ----- " + torneio + " -----\n");
                return torneio;
            })
            .map(this.torneioCreateInputPort::create)
            .map(torneio -> {
                System.out.println("\n\n ----- 6 ----- " + torneio + " -----\n");
                return torneio;
            })
            .map(this.torneioInMapper::toTorneioCreateDtoResponse)
            .map(torneio -> {
                System.out.println("\n\n ----- 7 ----- " + torneio + " -----\n");
                return torneio;
            })
            .orElseThrow();

        return ResponseEntity
            .created(URI.create("/api/v1/torneios" + response.id()))
            .body(response);
    }
}

