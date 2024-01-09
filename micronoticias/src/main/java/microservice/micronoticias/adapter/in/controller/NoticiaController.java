package microservice.micronoticias.adapter.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micronoticias.adapter.in.dto.request.NoticiaCadastrarDtoIn;
import microservice.micronoticias.adapter.in.dto.response.NoticiaCadastrarDtoOut;
import microservice.micronoticias.adapter.in.mapper.NoticiaMapperIn;
import microservice.micronoticias.application.port.input.NoticiaCadastrarInputPort;
import microservice.micronoticias.config.exception.ApiError;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/noticias")
@RequiredArgsConstructor
public class NoticiaController {

    private final NoticiaCadastrarInputPort cadastrarInputPort;

    private final NoticiaMapperIn mapperIn;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Cadastrar Notícia", description = "Recurso para cadastrar uma nova Notícia.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Recurso cadastrado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = NoticiaCadastrarDtoOut.class))),
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
    public ResponseEntity<NoticiaCadastrarDtoOut> cadastrar(@RequestBody @Valid NoticiaCadastrarDtoIn cadastrarDtoIn) {

        log.info("Recebida requisição para criar Notícia: {}", cadastrarDtoIn.titulo());

        var resposta = Optional.of(cadastrarDtoIn)
            .map(this.mapperIn::toNoticia)
            .map(this.cadastrarInputPort::cadastrar)
            .map(this.mapperIn::toNoticiaCadastrarDtoOut)
            .orElseThrow();

        log.info("Notícia criada com sucesso: {}", resposta.titulo());

        return ResponseEntity
            .created(URI.create("/api/v1/noticias/" + resposta.id()))
            .body(resposta);
    }
}
