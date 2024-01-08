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

import static org.junit.jupiter.api.Assertions.*;

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
}

