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

import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit CadastrarDtoIn - Notícia")
class NoticiaCadastrarDtoInUnitTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private Validator validator;

    private NoticiaCadastrarDtoIn.NoticiaCadastrarDtoInBuilder noticiaCadastrarDtoInBuilder;

    @BeforeEach
    void setUp() {
        noticiaCadastrarDtoInBuilder = factory.gerarNoticiaCadastrarDtoInBuilder();
    }

    @Test
    @DisplayName("notícia válida")
    void dadoNoticiaValida_QuandoInstanciar_EntaoRetornarDtoIn() {
        var dtoIn = noticiaCadastrarDtoInBuilder.build();
        Set<ConstraintViolation<NoticiaCadastrarDtoIn>> violations = validator.validate(dtoIn);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Nested
    @DisplayName("Chapéu")
    class Chapeu {

        @Test
        @DisplayName("nulo")
        void dadoChapeuNulo_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = noticiaCadastrarDtoInBuilder.chapeu(null).build();
            Set<ConstraintViolation<NoticiaCadastrarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoChapeuVazioOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCadastrarDtoInBuilder.chapeu(valor).build();
            Set<ConstraintViolation<NoticiaCadastrarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "31 abcde12345678901234567890qwe"})
        @DisplayName("com tamanho inválido")
        void dadoChapeuComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCadastrarDtoInBuilder.chapeu(valor).build();
            Set<ConstraintViolation<NoticiaCadastrarDtoIn>> violations = validator.validate(dtoIn);
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
            var dtoIn = noticiaCadastrarDtoInBuilder.titulo(null).build();
            Set<ConstraintViolation<NoticiaCadastrarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoTituloVazioOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCadastrarDtoInBuilder.titulo(valor).build();
            Set<ConstraintViolation<NoticiaCadastrarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "19 a1b2c3d4e5f6g7h8",
            "151 TestarLimiteMaximoDeCaracteresParaTitulo TestarLimiteMaximoDeCaracteresParaTitulo TestarLimiteMaximoDeCaracteresParaTitulo TestarLimiteMaximoDeCara"
        })
        @DisplayName("com tamanho inválido")
        void dadoTituloComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCadastrarDtoInBuilder.titulo(valor).build();
            Set<ConstraintViolation<NoticiaCadastrarDtoIn>> violations = validator.validate(dtoIn);
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
            var dtoIn = noticiaCadastrarDtoInBuilder.linhaFina(null).build();
            Set<ConstraintViolation<NoticiaCadastrarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoLinhaFinaVazioOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCadastrarDtoInBuilder.linhaFina(valor).build();
            Set<ConstraintViolation<NoticiaCadastrarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "79 TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresPa",
            "251 TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracteresParaLinhaFina TestarLimiteMaximoDeCaracte"
        })
        @DisplayName("com tamanho inválido")
        void dadoLinhaFinaComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = noticiaCadastrarDtoInBuilder.linhaFina(valor).build();
            Set<ConstraintViolation<NoticiaCadastrarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }
}

