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
import microservice.microinscricoes.adapter.in.controller.dto.request.InscricaoFiltroDto;
import microservice.microinscricoes.adapter.in.controller.dto.request.InscricaoOpenDtoIn;
import microservice.microinscricoes.adapter.in.controller.dto.response.InscricaoOpenDtoOut;
import microservice.microinscricoes.adapter.mapper.MapperIn;
import microservice.microinscricoes.application.port.input.InscricaoDeleteInputPort;
import microservice.microinscricoes.application.port.input.InscricaoCreateInputPort;
import microservice.microinscricoes.application.port.input.InscricaoPesquisarInputPort;
import org.apache.kafka.common.requests.ApiError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "Inscrições", description = "Contém os recursos de Abrir, Pesquisar, Atualizar e Deletar.")
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/inscricoes")
@RequiredArgsConstructor
public class InscricaoController {

    private static final String APPLICATION_YAML_VALUE = "application/x-yaml";

    private final InscricaoCreateInputPort inscricaoCreateInputPort;

    private final InscricaoPesquisarInputPort inscricaoPesquisarInputPort;

    private final InscricaoDeleteInputPort inscricaoDeleteInputPort;

    private final MapperIn mapperIn;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE, APPLICATION_YAML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    @Operation(summary = "Abrir Período", description = "Recurso para abrir período de inscrições.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created - Recurso cadastrado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = InscricaoOpenDtoOut.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição mal formulada.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found - Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Conflict - Desacordo com regras de negócio.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity - Recurso não processado por dados de entrada inválidos.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<InscricaoOpenDtoOut> open(
        @Parameter(name = "InscricaoOpenDtoIn", description = "Objeto para Transporte de Dados de entrada.", required = true)
        @RequestBody @Valid InscricaoOpenDtoIn inscricaoOpenDtoIn) {

        log.info("Recebida requisição para abrir período de Inscrições.");

        var response = Optional.of(inscricaoOpenDtoIn)
            .map(this.mapperIn::toInscricao)
            .map(this.inscricaoCreateInputPort::create)
            .map(this.mapperIn::toInscricaoOpenDtoOut)
            .orElseThrow();

        log.info("Período de Inscrições criado com sucesso, com Id: {}", response.getId());

        return ResponseEntity
            .created(URI.create("/api/v1/inscricoes/" + response.getId()))
            .body(response);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    @Operation(summary = "Pesquisar Produtos", description = "Recurso para pesquisar Produtos. A requisição exige Bearer Token. Acesso restrito para ADMINISTRADOR|ESTOQUISTA.",
//        security = {@SecurityRequirement(name = "security")},
        responses = {
            @ApiResponse(responseCode = "200", description = "OK - Requisição bem sucedida e com retorno.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = InscricaoOpenDtoOut.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição mal formulada.",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden - Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity - Recurso não processado por dados de entrada inválidos.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<Page<InscricaoOpenDtoOut>> pesquisar(
        @Parameter(name = "InscricaoFiltroDto", description = "Objeto para transporte de dados usados como filtros de pesquisa", required = false)
        @Valid final InscricaoFiltroDto filtroDto,
        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 5) final Pageable paginacao) {

        log.info("Requisição recebida para pesquisar Inscrições.");

        var response = Optional.of(filtroDto)
            .map(this.mapperIn::toInscricaoFiltro)
            .map(filtro -> this.inscricaoPesquisarInputPort.pesquisar(filtro, paginacao))
            .map(page -> page.map(this.mapperIn::toInscricaoOpenDtoOut))
            .orElseThrow();

        log.info("Pesquisa por inscrições concluída com sucesso.");

        return ResponseEntity
            .ok()
            .body(response);
    }

    @DeleteMapping(path = {"/{inscricaoId}"})
    @Operation(summary = "Deletar", description = "Recurso para apagar Inscrição.",
//        security = {@SecurityRequirement(name = "security")},
        responses = {
            @ApiResponse(responseCode = "204", description = "No Content - Requisição bem sucedida e sem retorno.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request - Requisição mal formulada.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden - Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found - Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<Void> delete(
            @Parameter(name = "id", description = "Chave de Identificação.", example = "78", required = true)
            @PathVariable(name = "inscricaoId") final Long id) {

        log.info("Requisição recebida para deletar Inscrição.");

        Optional.ofNullable(id)
            .ifPresentOrElse(this.inscricaoDeleteInputPort::deleteById,
                () -> {throw new NoSuchElementException();}
            );

        log.info("Inscrição deletada com sucesso, por Id: {}.", id);

        return ResponseEntity
            .noContent()
            .build();
    }
}

