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
@DisplayName("Unit EditoriaDtoIn - Editoria")
class EditoriaDtoInUnitTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private Validator validator;

    // fixture ou Scaffolding
    private EditoriaDtoIn.EditoriaDtoInBuilder editoriaDtoInBuilder;

    @BeforeEach
    void setUp() {
        editoriaDtoInBuilder = factory.gerarEditoriaDtoInBuilder();
    }

    @Test
    @DisplayName("editoria válida")
    void dadoEditoriaValida_QuandoInstanciar_EntaoRetornarDtoIn() {
        var dtoIn = editoriaDtoInBuilder.build();
        Set<ConstraintViolation<EditoriaDtoIn>> violations = validator.validate(dtoIn);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Nested
    @DisplayName("Id")
    class Id {

        @Test
        @DisplayName("nulo")
        void dadoIdNula_QuandoInstanciar_EntaoLancarException() {
            var idNegativo = -1L;
            var dtoIn = editoriaDtoInBuilder.id(idNegativo).build();
            Set<ConstraintViolation<EditoriaDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Nomenclatura")
    class Nomenclatura {

        @Test
        @DisplayName("nulo")
        void dadoNomenclaturaNula_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = editoriaDtoInBuilder.nomenclatura(null).build();
            Set<ConstraintViolation<EditoriaDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoNomenclaturaVaziaOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = editoriaDtoInBuilder.nomenclatura(valor).build();
            Set<ConstraintViolation<EditoriaDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "2_",
            "101 RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres__"})
        @DisplayName("com tamanho inválido")
        void dadoNomenclaturaComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = editoriaDtoInBuilder.nomenclatura(valor).build();
            Set<ConstraintViolation<EditoriaDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Descrição")
    class Descricao {

        @Test
        @DisplayName("nulo")
        void dadoDescricaoNula_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = editoriaDtoInBuilder.descricao(null).build();
            Set<ConstraintViolation<EditoriaDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoDescricaoVaziaOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = editoriaDtoInBuilder.descricao(valor).build();
            Set<ConstraintViolation<EditoriaDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "9 RegraDe",
            "201 RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres Regra"})
        @DisplayName("com tamanho inválido")
        void dadoDescricaoComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = editoriaDtoInBuilder.descricao(valor).build();
            Set<ConstraintViolation<EditoriaDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }
}

