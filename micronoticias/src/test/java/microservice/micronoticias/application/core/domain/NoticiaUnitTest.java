package microservice.micronoticias.application.core.domain;

import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ExceptionCollector;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit Domain - Noticia")
class NoticiaUnitTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    private Noticia noticia;

    @BeforeEach
    void criarCenario() {

        noticia = factory.gerarNoticia(30, 150, 250, 400, 5000, 1, 100, 1, 250);
    }

    @Nested
    @DisplayName("Chapéu")
    class Chapeu {

        @Test
        @DisplayName("nulo")
        void dadoChapeuNulo_QuandoInstanciarNoticia_EntaoLancarException() {
            Executable acao = () -> noticia.setChapeu(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("")
        void dadoChapeuVazioOuEmBranco_QuandoInstanciarNoticia_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setChapeu(valor);
            Assertions.assertThrows(CampoVazioOuEmBrancoProibidoException.class, acao);
        }
    }
}

