package microservice.microusuarios.adapter.in.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Persons", description = "Contém os recursos de Cadastrar, Pesquisar, Atualizar e Deletar.")
@Slf4j
@RestController
@RequestMapping(path = {"/api/v1/persons"})
@RequiredArgsConstructor
public class PersonController {

    @GetMapping
    public ResponseEntity<String> status() {

        return ResponseEntity
            .ok()
            .body("OK");
    }
}

