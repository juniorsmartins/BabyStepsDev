package microservice.micronoticias.application.core.domain;

import microservice.micronoticias.config.exception.http_409.CampoComTamanhoInvalidoException;
import microservice.micronoticias.config.exception.http_409.CampoNuloProibidoException;
import microservice.micronoticias.config.exception.http_409.CampoVazioOuEmBrancoProibidoException;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit Domain - Noticia")
class NoticiaUnitTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    // fixture ou Scaffolding
    private Noticia noticia;

    @BeforeEach
    void setUp() {
        noticia = factory.gerarNoticia(30, 150, 250, 400, 5000, 1, 100, 1, 250);
    }

    @Nested
    @DisplayName("Chapéu")
    class Chapeu {

        @Test
        @DisplayName("nulo")
        void dadoChapeuNulo_QuandoSettar_EntaoLancarException() {
            Executable acao = () -> noticia.setChapeu(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoChapeuVazioOuEmBranco_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setChapeu(valor);
            Assertions.assertThrows(CampoVazioOuEmBrancoProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"a", "31 abcde12345678901234567890qwe"})
        @DisplayName("tamanho inválido")
        void dadoChapeuMaiorOuMenorQueLimites_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setChapeu(valor);
            Assertions.assertThrows(CampoComTamanhoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Título")
    class Titulo {

        @Test
        @DisplayName("nulo")
        void dadoTituloNulo_QuandoSettar_EntaoLancarException() {
            Executable acao = () -> noticia.setTitulo(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoTituloVazioOuEmBranco_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setTitulo(valor);
            Assertions.assertThrows(CampoVazioOuEmBrancoProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "19 a1b2c3d4e5f6g7h8",
                "151 TestarLimiteMaximoDeCaracteresParaTitulo TestarLimiteMaximoDeCaracteresParaTitulo TestarLimiteMaximoDeCaracteresParaTitulo TestarLimiteMaximoDeCara"
        })
        @DisplayName("tamanho inválido")
        void dadoTituloMaiorOuMenorQueLimites_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setTitulo(valor);
            Assertions.assertThrows(CampoComTamanhoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("LinhaFina")
    class LinhaFina {

        @Test
        @DisplayName("nulo")
        void dadoLinhaFinaNulo_QuandoSettar_EntaoLancarException() {
            Executable acao = () -> noticia.setLinhaFina(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoLinhaFinaVazioOuEmBranco_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setLinhaFina(valor);
            Assertions.assertThrows(CampoVazioOuEmBrancoProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "79 TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresPa",
            "251 TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracte"
        })
        @DisplayName("tamanho inválido")
        void dadoLinhaFinaMaiorOuMenorQueLimites_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setLinhaFina(valor);
            Assertions.assertThrows(CampoComTamanhoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Lide")
    class Lide {

        @Test
        @DisplayName("nulo")
        void dadoLideNulo_QuandoSettar_EntaoLancarException() {
            Executable acao = () -> noticia.setLide(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoLideVazioOuEmBranco_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setLide(valor);
            Assertions.assertThrows(CampoVazioOuEmBrancoProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "79 TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresPa",
            "401 TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina__"
        })
        @DisplayName("tamanho inválido")
        void dadoLideMaiorOuMenorQueLimites_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setLide(valor);
            Assertions.assertThrows(CampoComTamanhoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Corpo")
    class Corpo {

        @Test
        @DisplayName("nulo")
        void dadoCorpoNulo_QuandoSettar_EntaoLancarException() {
            Executable acao = () -> noticia.setCorpo(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoCorpoVazioOuEmBranco_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setCorpo(valor);
            Assertions.assertThrows(CampoVazioOuEmBrancoProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "99 TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaxi",
            "5001 TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo  TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaC"
        })
        @DisplayName("tamanho inválido")
        void dadoCorpoMaiorOuMenorQueLimites_QuandoSettar_EntaoLancarException(String valor) {
            Executable acao = () -> noticia.setCorpo(valor);
            Assertions.assertThrows(CampoComTamanhoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Autorias")
    class Autorias {

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoAutoriasVazioOuEmBranco_QuandoSettar_EntaoLancarException(String valor) {
            var autorias = List.of(valor);
            Executable acao = () -> noticia.setAutorias(autorias);
            Assertions.assertThrows(CampoVazioOuEmBrancoProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "2_",
            "101 TestarLimiteMaximoDeCaracteresParaAutoria TestarLimiteMaximoDeCaracteresParaAutoria TestarLimiteM"
        })
        @DisplayName("tamanho inválido")
        void dadoAutoriasMaiorOuMenorQueLimites_QuandoSettar_EntaoLancarException(String valor) {
            var autorias = List.of(valor);
            Executable acao = () -> noticia.setAutorias(autorias);
            Assertions.assertThrows(CampoComTamanhoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Fontes")
    class Fontes {

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoFontesVazioOuEmBranco_QuandoSettar_EntaoLancarException(String valor) {
            var fontes = List.of(valor);
            Executable acao = () -> noticia.setFontes(fontes);
            Assertions.assertThrows(CampoVazioOuEmBrancoProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "2_",
            "251 TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes__"
        })
        @DisplayName("tamanho inválido")
        void dadoFontesMaiorOuMenorQueLimites_QuandoSettar_EntaoLancarException(String valor) {
            var fontes = List.of(valor);
            Executable acao = () -> noticia.setFontes(fontes);
            Assertions.assertThrows(CampoComTamanhoInvalidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "2_",
            "251 TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes__"
        })
        @DisplayName("dois itens com tamanho inválido")
        void dadoDuasFontesMaiorOuMenorQueLimites_QuandoSettar_EntaoLancarException(String valor) {
            var valor2 = RandomStringUtils.randomAlphabetic(200);
            var fontes = List.of(valor, valor2);
            Executable acao = () -> noticia.setFontes(fontes);
            Assertions.assertThrows(CampoComTamanhoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Editorias")
    class Editorias {

        @Test
        @DisplayName("nulo")
        void dadoEditoriasNulo_QuandoSettar_EntaoLancarException() {
            Executable acao = () -> noticia.setEditorias(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }
    }
}

