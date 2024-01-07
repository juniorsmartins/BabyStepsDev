package microservice.micronoticias.application.core.domain;

import microservice.micronoticias.config.exception.http_409.CampoComTamanhoInvalidoException;
import microservice.micronoticias.config.exception.http_409.CampoNuloProibidoException;
import microservice.micronoticias.config.exception.http_409.CampoVazioOuEmBrancoProibidoException;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        void dadoChapeuNulo_QuandoSettarChapeu_EntaoLancarException() {
            Executable acao = () -> noticia.setChapeu(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoChapeuVazioOuEmBranco_QuandoSettarChapeu_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setChapeu(valor);
            Assertions.assertThrows(CampoVazioOuEmBrancoProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"a", "abcde12345678901234567890qwerty"})
        @DisplayName("tamanho inválido")
        void dadoChapeuMaiorOuMenorQueLimites_QuandoSettarChapeu_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setChapeu(valor);
            Assertions.assertThrows(CampoComTamanhoInvalidoException.class, acao);
        }
    }
}

