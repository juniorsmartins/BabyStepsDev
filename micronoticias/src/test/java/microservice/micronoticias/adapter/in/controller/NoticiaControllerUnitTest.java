package microservice.micronoticias.adapter.in.controller;

import microservice.micronoticias.application.port.input.NoticiaDeleteInputPort;
import microservice.micronoticias.application.port.input.NoticiaUpdateInputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit Controller - Notícia")
class NoticiaControllerUnitTest {

    @Mock
    private NoticiaUpdateInputPort noticiaUpdateInputPort;

    @Mock
    private NoticiaDeleteInputPort noticiaDeleteInputPort;

    @InjectMocks
    private NoticiaController noticiaController;

    @Nested
    @DisplayName("Delete")
    class Delete {

        @Test
        @DisplayName("id nulo")
        void dadoIdNulo_QuandoDelete_EntaoLancarException() {
            Executable acao = () -> noticiaController.delete(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
            Mockito.verifyNoInteractions(noticiaDeleteInputPort);
        }
    }
}

