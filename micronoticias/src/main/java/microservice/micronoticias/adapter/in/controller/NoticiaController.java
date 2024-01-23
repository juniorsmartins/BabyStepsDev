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
import microservice.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import microservice.micronoticias.adapter.in.dto.request.NoticiaUpdateDtoIn;
import microservice.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import microservice.micronoticias.adapter.in.dto.response.NoticiaUpdateDtoOut;
import microservice.micronoticias.adapter.in.mapper.NoticiaMapperIn;
import microservice.micronoticias.application.port.input.NoticiaCriarInputPort;
import microservice.micronoticias.application.port.input.NoticiaDeleteInputPort;
import microservice.micronoticias.application.port.input.NoticiaUpdateInputPort;
import microservice.micronoticias.config.exception.ApiError;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "Noticias", description = "Contém os recursos de Cadastrar, Pesquisar, Atualizar e Deletar.")
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/noticias")
@RequiredArgsConstructor
public class NoticiaController {

    private static final String APPLICATION_YAML_VALUE = "application/x-yaml";

    private final NoticiaCriarInputPort cadastrarInputPort;

    private final NoticiaUpdateInputPort updateInputPort;

    private final NoticiaDeleteInputPort deleteInputPort;

    private final NoticiaMapperIn mapperIn;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Cadastrar", description = "Recurso para criar uma nova Notícia.",
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
    public ResponseEntity<NoticiaCriarDtoOut> create(
        @Parameter(name = "NoticiaCriarDtoIn", description = "Objeto para Transporte de Dados de entrada.", required = true)
        @RequestBody @Valid NoticiaCriarDtoIn criarDtoIn) {

        log.info("Recebida requisição para criar Notícia.");

        var resposta = Optional.of(criarDtoIn)
            .map(this.mapperIn::toNoticia)
            .map(this.cadastrarInputPort::criar)
            .map(this.mapperIn::toNoticiaCadastrarDtoOut)
            .orElseThrow();

        log.info("Notícia criada com sucesso: {}", resposta.titulo());

        return ResponseEntity
            .created(URI.create("/api/v1/noticias/" + resposta.id()))
            .body(resposta);
    }

    @PutMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Atualizar", description = "Recurso para atualizar Notícia.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida e com retorno.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = NoticiaUpdateDtoOut.class))),
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
    public ResponseEntity<NoticiaUpdateDtoOut> update(
        @Parameter(name = "NoticiaUpdateDtoIn", description = "Objeto para Transporte de Dados de entrada.", required = true)
        @RequestBody @Valid NoticiaUpdateDtoIn updateDtoIn) {

        log.info("Requisição recebida para atualizar Noticia.");

        var response = Optional.of(updateDtoIn)
            .map(this.mapperIn::toNoticia)
            .map(this.updateInputPort::update)
            .map(this.mapperIn::toNoticiaUpdateDtoOut)
            .orElseThrow();

        log.info("Sucesso ao atualizar Noticia com Id: {}.", response.id());

        return ResponseEntity
            .ok()
            .body(response);
    }

    @DeleteMapping(path = {"/{noticiaId}"},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    @Operation(summary = "Deletar por Id", description = "Recurso para apagar Notícia.",
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
    public ResponseEntity<Void> delete(
        @Parameter(name = "id", description = "Chave de Identificação.", example = "78", required = true)
        @PathVariable(name = "noticiaId") final Long id) {

        log.info("Requisição recebida para deletar Notícia por Id.");

        Optional.ofNullable(id)
            .ifPresentOrElse(this.deleteInputPort::deleteById,
                () -> {throw new NoSuchElementException();}
            );

        log.info("Sucesso ao deletar Notícia por Id: {}.", id);

        return ResponseEntity
            .noContent()
            .build();
    }
}

