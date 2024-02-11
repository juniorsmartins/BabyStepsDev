package microservice.microtorneios.adapters.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.in.dto.request.TorneioCreateDtoRequest;
import microservice.microtorneios.adapters.in.dto.response.TorneioCreateDtoResponse;
import microservice.microtorneios.adapters.in.mapper.TorneioMapperIn;
import microservice.microtorneios.application.port.input.TorneioCreateInputPort;
import org.apache.kafka.common.requests.ApiError;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Tag(name = "Torneio", description = "Contém os recursos de Cadastrar, Pesquisar, Atualizar e Deletar.")
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/torneios")
@RequiredArgsConstructor
public class TorneioController {

    private static final String APPLICATION_YAML_VALUE = "application/x-yaml";

    private final TorneioCreateInputPort torneioCreateInputPort;

    private final TorneioMapperIn mapperIn;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE, APPLICATION_YAML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    @Operation(summary = "Abrir Período", description = "Recurso para abrir período de inscrições.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Recurso cadastrado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = TorneioCreateDtoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição mal formulada.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com regras de negócio.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<TorneioCreateDtoResponse> create(
        @Parameter(name = "TorneioCreateDtoRequest", description = "Objeto para Transporte de Dados de entrada.", required = true)
        @RequestBody @Valid TorneioCreateDtoRequest torneioCreateDtoRequest) {

        log.info("Recebida requisição para cadastrar Torneio.");

        var response = Optional.of(torneioCreateDtoRequest)
            .map(this.mapperIn::toTorneio)
            .map(this.torneioCreateInputPort::create)
            .map(this.mapperIn::toTorneioCreateDtoResponse)
            .orElseThrow();

        log.info("Concluída requisição para cadastrar Torneio, com Id: {}", response.id());

        return ResponseEntity
            .created(URI.create("/api/v1/torneios" + response.id()))
            .body(response);
    }
}

