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
@DisplayName("Unit EditoriaCriarDtoIn - Editoria")
class EditoriaCriarDtoInUnitTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    Validator validator;

    private EditoriaCriarDtoIn.EditoriaCriarDtoInBuilder editoriaCriarDtoInBuilder;

    @BeforeEach
    void setUp() {
        editoriaCriarDtoInBuilder = factory.gerarEditoriaCriarDtoInBuilder();
    }

    @Test
    @DisplayName("editoria válida")
    void dadoEditoriaValida_QuandoInstanciar_EntaoRetornarDtoIn() {
        var dtoIn = editoriaCriarDtoInBuilder.build();
        Set<ConstraintViolation<EditoriaCriarDtoIn>> violations = validator.validate(dtoIn);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Nested
    @DisplayName("Nomenclatura")
    class Nomenclatura {

        @Test
        @DisplayName("nulo")
        void dadoNomenclaturaNula_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = editoriaCriarDtoInBuilder.nomenclatura(null).build();
            Set<ConstraintViolation<EditoriaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoNomenclaturaVaziaOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = editoriaCriarDtoInBuilder.nomenclatura(valor).build();
            Set<ConstraintViolation<EditoriaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {"2_", "101 RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres__"})
        @DisplayName("com tamanho inválido")
        void dadoNomenclaturaComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = editoriaCriarDtoInBuilder.nomenclatura(valor).build();
            Set<ConstraintViolation<EditoriaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }
}

