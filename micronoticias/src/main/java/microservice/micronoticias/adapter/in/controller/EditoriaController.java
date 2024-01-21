package microservice.micronoticias.adapter.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.in.dto.request.EditoriaCriarDtoIn;
import microservice.micronoticias.adapter.in.dto.request.EditoriaUpdateDtoIn;
import microservice.micronoticias.adapter.in.dto.response.EditoriaCriarDtoOut;
import microservice.micronoticias.adapter.in.dto.response.EditoriaListarDtoOut;
import microservice.micronoticias.adapter.in.dto.response.EditoriaUpdateDtoOut;
import microservice.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import microservice.micronoticias.adapter.in.mapper.EditoriaMapperIn;
import microservice.micronoticias.application.port.input.EditoriaCriarInputPort;
import microservice.micronoticias.application.port.input.EditoriaDeletarPorIdInputPort;
import microservice.micronoticias.application.port.input.EditoriaListarInputPort;
import microservice.micronoticias.application.port.input.EditoriaUpdateInputPort;
import microservice.micronoticias.config.exception.ApiError;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "Editorias", description = "Contém os recursos de Cadastrar, Listar, Atualizar e Deletar.")
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/editorias")
@RequiredArgsConstructor
public class EditoriaController {

    private static final String APPLICATION_YAML_VALUE = "application/x-yaml";

    private final EditoriaCriarInputPort editoriaCriarInputPort;

    private final EditoriaUpdateInputPort editoriaUpdateInputPort;

    private final EditoriaListarInputPort editoriaListarInputPort;

    private final EditoriaDeletarPorIdInputPort editoriaDeletarPorIdInputPort;

    private final EditoriaMapperIn mapperIn;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE, APPLICATION_YAML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    @Operation(summary = "Cadastrar", description = "Recurso para criar uma nova Editoria.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Recurso cadastrado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = NoticiaCriarDtoOut.class))),
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
    public ResponseEntity<EditoriaCriarDtoOut> criar (
        @Parameter(name = "EditoriaCriarDtoIn", description = "Objeto para Transporte de Dados de entrada.", required = true)
        @RequestBody @Valid EditoriaCriarDtoIn criarDtoIn) {

        log.info("Recebida requisição para criar Editoria.");

        var resposta = Optional.of(criarDtoIn)
            .map(this.mapperIn::toEditoria)
            .map(this.editoriaCriarInputPort::criar)
            .map(this.mapperIn::toEditoriaCriarDtoOut)
            .orElseThrow();

        log.info("Editoria criada com sucesso: {}", resposta.nomenclatura());

        return ResponseEntity
            .created(URI.create("/api/v1/noticias/" + resposta.id()))
            .body(resposta);
    }

    @PutMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    @Operation(summary = "Atualizar", description = "Recurso para atualizar Editoria.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida e com retorno.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = EditoriaUpdateDtoOut.class))),
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
    public ResponseEntity<EditoriaUpdateDtoOut> update(
        @Parameter(name = "EditoriaUpdateDtoIn", description = "Objeto para transporte de dados para atualizar.", required = true)
        @RequestBody @Valid EditoriaUpdateDtoIn updateDtoIn) {

        log.info("Requisição recebida para atualizar Editoria.");

        var response = Optional.of(updateDtoIn)
            .map(this.mapperIn::toEditoria)
            .map(this.editoriaUpdateInputPort::update)
            .map(this.mapperIn::toEditoriaUpdateDtoOut)
            .orElseThrow();

        log.info("Sucesso ao atualizar Editoria com Id: {}.", response.id());

        return ResponseEntity
            .ok()
            .body(response);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    @Operation(summary = "Listar", description = "Recurso para listar Editorias.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Recursos listados com sucesso.",
                content = @Content(mediaType = "application/json", array =
                    @ArraySchema(schema = @Schema(implementation = EditoriaListarDtoOut.class)))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<List<EditoriaListarDtoOut>> listar() {

        log.info("Requisição recebida para listar Editorias.");

        var response = this.editoriaListarInputPort.listar()
            .stream()
            .map(this.mapperIn::toEditoriaListarDtoOut)
            .toList();

        log.info("Editorias listadas com sucesso.");

        return ResponseEntity
            .ok()
            .body(response);
    }

    @DeleteMapping(path = {"/{produtoId}"},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    @Operation(summary = "Deletar por Id", description = "Recurso para apagar Editoria.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Requisição bem sucedida e sem retorno.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Requisição mal formulada.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<Void> deletarPorId(
        @Parameter(name = "id", description = "Chave de Identificação.", example = "78", required = true)
        @PathVariable(name = "produtoId") final Long id) {

        log.info("Requisição recebida para deletar Editoria por Id.");

        Optional.ofNullable(id)
            .ifPresentOrElse(this.editoriaDeletarPorIdInputPort::deletarPorId,
                () -> {throw new NoSuchElementException();}
            );

        log.info("Sucesso ao deletar Editoria por Id: {}.", id);

        return ResponseEntity
            .noContent()
            .build();
    }
}

