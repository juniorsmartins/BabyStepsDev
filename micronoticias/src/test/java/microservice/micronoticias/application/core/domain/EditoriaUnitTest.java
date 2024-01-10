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
@DisplayName("Unit Domain - Editoria")
class EditoriaUnitTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    private Editoria editoria;

    @BeforeEach
    void setUp() {
        editoria = factory.gerarEditoria();
    }

    @Nested
    @DisplayName("Nomenclatura")
    class Nomenclatura {

        @Test
        @DisplayName("nulo")
        void dadoNomenclaturaNula_QuandoSettar_EntaoLancarException() {
            Executable acao = () -> editoria.setNomenclatura(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "    "})
        @DisplayName("vazio ou em branco")
        void dadoNomenclaturaVaziaOuEmBranco_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> editoria.setNomenclatura(valor);
            Assertions.assertThrows(CampoVazioOuEmBrancoProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "2_",
            "101 RegraDeLimiteMaximoDeCaracteresParaCampoNomenclatura RegraDeLimiteMaximoDeCaracteresParaCampoNome"}
        )
        @DisplayName("tamanho inválido")
        void dadoNomenclaturaMaiorOuMenorQueLimites_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> editoria.setNomenclatura(valor);
            Assertions.assertThrows(CampoComTamanhoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Descrição")
    class Descricao {

        @Test
        @DisplayName("nulo")
        void dadoDescricaoNula_QuandoSettar_EntaoLancarException() {
            Executable acao = () -> editoria.setDescricao(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "    "})
        @DisplayName("vazio ou em branco")
        void dadoDescricaoVaziaOuEmBranco_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> editoria.setDescricao(valor);
            Assertions.assertThrows(CampoVazioOuEmBrancoProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "9 Limite_",
            "201 RegraDeLimiteMaximoDeCaracteresParaCampoDescrição RegraDeLimiteMaximoDeCaracteresParaCampoDescrição RegraDeLimiteMaximoDeCaracteresParaCampoDescrição RegraDeLimiteMaximoDeCaracteresParaCampoDescriç"})
        @DisplayName("tamanho inválido")
        void dadoDescricaoMaiorOuMenorQueLimites_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> editoria.setDescricao(valor);
            Assertions.assertThrows(CampoComTamanhoInvalidoException.class, acao);
        }
    }
}