package microservice.micronoticias.adapter.in.controller;

import microservice.micronoticias.application.port.input.EditoriaDeletarPorIdInputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit Controller - DeletePorId Editoria")
class EditoriaControllerUnitTest {

    @Mock
    private EditoriaDeletarPorIdInputPort editoriaDeletarPorIdInputPort;

    @InjectMocks
    private EditoriaController editoriaController;

    @Test
    @DisplayName("id nulo")
    void dadoIdNulo_QuandoCriarComContentNegotiationJSon_EntaoRetornarDadosIguaisSalvos() {
        Executable acao = () -> this.editoriaController.deletarPorId(null);
        Assertions.assertThrows(NoSuchElementException.class, acao);
    }
}

