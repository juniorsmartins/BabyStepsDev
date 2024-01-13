package microservice.micronoticias.adapter.in.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit NoticiaCriarDtoIn - Notícia")
class NoticiaCriarDtoInUnitTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private Validator validator;

    // fixture ou Scaffolding
    private NoticiaCriarDtoIn.NoticiaCriarDtoInBuilder noticiaCriarDtoInBuilder;

    @BeforeEach
    void setUp() {
        noticiaCriarDtoInBuilder = factory.gerarNoticiaCriarDtoInBuilder();
    }

    @Test
    @DisplayName("notícia válida")
    void dadoNoticiaValida_QuandoInstanciar_EntaoRetornarDtoIn() {
        var dtoIn = noticiaCriarDtoInBuilder.build();
        Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Nested
    @DisplayName("Chapéu")
    class Chapeu {

        @Test
        @DisplayName("nulo")
        void dadoChapeuNulo_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = noticiaCriarDtoInBuilder.chapeu(null).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoChapeuVazioOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.chapeu(valor).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "31 abcde12345678901234567890qwe"})
        @DisplayName("com tamanho inválido")
        void dadoChapeuComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.chapeu(valor).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Título")
    class Titulo {

        @Test
        @DisplayName("nulo")
        void dadoTituloNulo_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = noticiaCriarDtoInBuilder.titulo(null).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoTituloVazioOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.titulo(valor).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "19 a1b2c3d4e5f6g7h8",
            "151 TestarLimiteMaximoDeCaracteresParaTitulo TestarLimiteMaximoDeCaracteresParaTitulo TestarLimiteMaximoDeCaracteresParaTitulo TestarLimiteMaximoDeCara"
        })
        @DisplayName("com tamanho inválido")
        void dadoTituloComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.titulo(valor).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("LinhaFina")
    class LinhaFina {

        @Test
        @DisplayName("nulo")
        void dadoLinhaFinaNulo_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = noticiaCriarDtoInBuilder.linhaFina(null).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoLinhaFinaVazioOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.linhaFina(valor).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "79 TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresPa",
            "251 TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracte"
        })
        @DisplayName("com tamanho inválido")
        void dadoLinhaFinaComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.linhaFina(valor).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Lide")
    class Lide {

        @Test
        @DisplayName("nulo")
        void dadoLideNulo_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = noticiaCriarDtoInBuilder.lide(null).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoLideVazioOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.lide(valor).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "79 TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresPa",
            "401 TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina__"
        })
        @DisplayName("com tamanho inválido")
        void dadoLideComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.lide(valor).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Corpo")
    class Corpo {

        @Test
        @DisplayName("nulo")
        void dadoCorpoNulo_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = noticiaCriarDtoInBuilder.corpo(null).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoCorpoVazioOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.corpo(valor).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "99 TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaxi",
            "5001 TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo  TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaCorpo TestarLimiteMaximoDeCaracteresParaC"
        })
        @DisplayName("com tamanho inválido")
        void dadoCorpoComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.corpo(valor).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Autorias")
    class Autorias {

        @Test
        @DisplayName("nulo")
        void dadoAutoriasNulo_QuandoInstanciar_EntaoRetornarDtoIn() {
            var dtoIn = noticiaCriarDtoInBuilder.autorias(null).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertTrue(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "     "})
        @DisplayName("vazio ou em branco")
        void dadoAutoriasComValoresVaziosOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.autorias(List.of(valor)).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "2_",
            "101 TestarLimiteMaximoDeCaracteresParaAutoria TestarLimiteMaximoDeCaracteresParaAutoria TestarLimiteM"
        })
        @DisplayName("tamanho inválido")
        void dadoAutoriasComValoresComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.autorias(List.of(valor)).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Fontes")
    class Fontes {

        @Test
        @DisplayName("nulo")
        void dadoFontesNulo_QuandoInstanciar_EntaoRetornarDtoIn() {
            var dtoIn = noticiaCriarDtoInBuilder.fontes(null).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertTrue(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "     "})
        @DisplayName("vazio ou em branco")
        void dadoFontesComValoresVaziosOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.fontes(List.of(valor)).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "2_",
            "251 TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes TestarLimiteMaximoDeCaracteresParaFontes__"
        })
        @DisplayName("tamanho inválido")
        void dadoFontesComValoresComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCriarDtoInBuilder.fontes(List.of(valor)).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Editorias")
    class Editorias {

        @Test
        @DisplayName("nulo")
        void dadoEditoriasNulo_QuandoInstanciar_EntaoRetornarDtoIn() {
            var dtoIn = noticiaCriarDtoInBuilder.editorias(null).build();
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertTrue(violations.isEmpty());
        }
    }
}

