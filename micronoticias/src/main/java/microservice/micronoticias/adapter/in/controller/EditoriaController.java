package microservice.micronoticias.adapter.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.in.dto.request.EditoriaCriarDtoIn;
import microservice.micronoticias.adapter.in.dto.response.EditoriaCriarDtoOut;
import microservice.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import microservice.micronoticias.adapter.in.mapper.EditoriaMapperIn;
import microservice.micronoticias.application.port.input.EditoriaCriarInputPort;
import microservice.micronoticias.config.exception.ApiError;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Tag(name = "Editorias", description = "Contém os recursos de Cadastrar, Pesquisar, Atualizar e Deletar.")
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/editorias")
@RequiredArgsConstructor
public class EditoriaController {

    private final EditoriaCriarInputPort editoriaCriarInputPort;

    private final EditoriaMapperIn mapperIn;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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

        log.info("Recebida requisição para criar Editoria: {}", criarDtoIn.nomenclatura());

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
}

