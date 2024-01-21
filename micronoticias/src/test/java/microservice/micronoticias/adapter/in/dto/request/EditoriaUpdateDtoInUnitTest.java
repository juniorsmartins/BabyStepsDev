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
@DisplayName("Unit EditoriaUpdateDtoIn - Editoria")
class EditoriaUpdateDtoInUnitTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private Validator validator;

    private EditoriaUpdateDtoIn.EditoriaUpdateDtoInBuilder editoriaUpdateDtoInBuilder;

    @BeforeEach
    void setUp() {
        editoriaUpdateDtoInBuilder = factory.gerarEditoriaUpdateDtoInBuilder();
    }

    @Test
    @DisplayName("dados válidos")
    void dadoEditoriaValida_QuandoInstanciar_EntaoRetornarDtoIn() {
        var dtoIn = this.editoriaUpdateDtoInBuilder.build();
        Set<ConstraintViolation<EditoriaUpdateDtoIn>> violations = this.validator.validate(dtoIn);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Nested
    @DisplayName("Id")
    class EditoriaId {

        @Test
        @DisplayName("nulo")
        void dadoIdNula_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = editoriaUpdateDtoInBuilder.id(null).build();
            Set<ConstraintViolation<EditoriaUpdateDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertEquals(1, violations.size());
        }

        @Test
        @DisplayName("negativo")
        void dadoIdNegativo_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = editoriaUpdateDtoInBuilder.id(-1L).build();
            Set<ConstraintViolation<EditoriaUpdateDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertEquals(1, violations.size());
        }

        @Test
        @DisplayName("zero")
        void dadoIdZerpo_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = editoriaUpdateDtoInBuilder.id(0L).build();
            Set<ConstraintViolation<EditoriaUpdateDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Nomenclatura")
    class Nomenclatura {

        @Test
        @DisplayName("nulo")
        void dadoNomenclaturaNula_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = editoriaUpdateDtoInBuilder.nomenclatura(null).build();
            Set<ConstraintViolation<EditoriaUpdateDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoNomenclaturaVaziaOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = editoriaUpdateDtoInBuilder.nomenclatura(valor).build();
            Set<ConstraintViolation<EditoriaUpdateDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "2_",
            "101 RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres__"})
        @DisplayName("com tamanho inválido")
        void dadoNomenclaturaComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = editoriaUpdateDtoInBuilder.nomenclatura(valor).build();
            Set<ConstraintViolation<EditoriaUpdateDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Descrição")
    class Descricao {

        @Test
        @DisplayName("nulo")
        void dadoDescricaoNula_QuandoInstanciar_EntaoLancarException() {
            var dtoIn = editoriaUpdateDtoInBuilder.descricao(null).build();
            Set<ConstraintViolation<EditoriaUpdateDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoDescricaoVaziaOuEmBranco_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = editoriaUpdateDtoInBuilder.descricao(valor).build();
            Set<ConstraintViolation<EditoriaUpdateDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {
            "9 RegraDe",
            "201 RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres RegraDeLimiteMaximoDeCaracteres Regra"})
        @DisplayName("com tamanho inválido")
        void dadoDescricaoComTamanhoInvalido_QuandoInstanciar_EntaoLancarException(String valor) {
            var dtoIn = editoriaUpdateDtoInBuilder.descricao(valor).build();
            Set<ConstraintViolation<EditoriaUpdateDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertEquals(1, violations.size());
        }
    }
}

