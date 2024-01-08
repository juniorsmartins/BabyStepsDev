package microservice.micronoticias.adapter.in.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import microservice.micronoticias.adapter.in.dto.request.NoticiaCadastrarDtoIn;
import microservice.micronoticias.adapter.in.dto.response.NoticiaCadastrarDtoOut;
import microservice.micronoticias.adapter.in.mapper.NoticiaMapperIn;
import microservice.micronoticias.adapter.in.mapper.NoticiaMapperOut;
import microservice.micronoticias.application.port.input.NoticiaCadastrarInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/noticias")
@RequiredArgsConstructor
public class NoticiaController {

    private final NoticiaCadastrarInputPort cadastrarInputPort;

    private final NoticiaMapperIn mapperIn;

    private final NoticiaMapperOut mapperOut;

    @PostMapping
    public ResponseEntity<NoticiaCadastrarDtoOut> cadastrar(@RequestBody @Valid NoticiaCadastrarDtoIn cadastrarDtoIn) {

        var resposta = Optional.of(cadastrarDtoIn)
            .map(this.mapperIn::toNoticia)
            .map(this.cadastrarInputPort::cadastrar)
            .map(this.mapperOut::toNoticiaCadastrarDtoOut)
            .orElseThrow();

        return ResponseEntity
            .created(URI.create("/api/v1/noticias/" + resposta.id()))
            .body(resposta);
    }
}

