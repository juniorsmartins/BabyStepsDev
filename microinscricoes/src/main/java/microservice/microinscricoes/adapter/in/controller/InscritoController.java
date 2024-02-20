package microservice.microinscricoes.adapter.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microinscricoes.adapter.in.dto.request.InscritoRegisterDtoIn;
import microservice.microinscricoes.adapter.in.dto.response.InscritoRegisterDtoOut;
import microservice.microinscricoes.adapter.in.mapper.MapperIn;
import microservice.microinscricoes.application.port.input.InscritoRegisterInputPort;
import org.apache.kafka.common.requests.ApiError;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Tag(name = "Inscritos", description = "Contém os recursos de Abrir, Cadastrar, Consultar, Listar, Atualizar e Deletar.")
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/inscritos")
@RequiredArgsConstructor
public class InscritoController {

    private static final String APPLICATION_YAML_VALUE = "application/x-yaml";

    private final InscritoRegisterInputPort inscritoRegisterInputPort;

    private final MapperIn mapperIn;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE, APPLICATION_YAML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    @Operation(summary = "Registrar Inscrito", description = "Recurso para registrar Inscrito.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created - Recurso cadastrado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = InscritoRegisterDtoOut.class))),
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
    public ResponseEntity<InscritoRegisterDtoOut> register(
        @Parameter(name = "InscricaoRegisterDtoIn", description = "Objeto para Transporte de Dados de entrada.", required = true)
        @RequestBody @Valid InscritoRegisterDtoIn inscritoRegisterDtoIn) {

        log.info("Requisição recebida para registrar um Inscrito.");

        var response = Optional.of(inscritoRegisterDtoIn)
            .map(this.mapperIn::toInscrito)
            .map(this.inscritoRegisterInputPort::register)
            .map(this.mapperIn::toInscritoRegisterDtoOut)
            .orElseThrow();

        log.info("Sucesso ao registrar um Inscrito, com Id: {}.", response.id());

        return ResponseEntity
            .created(URI.create("/api/v1/inscritos/" + response.id()))
            .body(response);
    }
}

