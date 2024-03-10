package microservice.microtimes.adapter.in.controller.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import microservice.microtimes.utility.AbstractTestcontainersTest;
import microservice.microtimes.utility.FactoryObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
@DisplayName("Unit - TimeCreateDtoRequest")
class TimeCreateDtoRequestUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private Validator validator;

    @Nested
    @DisplayName("nome fantasia")
    class NomeFantasia {

        @Test
        @DisplayName("nulo")
        void dadoNomeFantasiaNulo_quandoInstanciar_entaoLancarException() {
            var dtoRequest = factory.gerarTimeCreateDtoRequestBuilder()
                .nomeFantasia(null)
                .build();

            Set<ConstraintViolation<TimeCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoNomeFantasiaVazioOuEmBranco_quandoInstancear_entaoLancarException(String valor) {
            var dtoRequest = factory.gerarTimeCreateDtoRequestBuilder()
                .nomeFantasia(valor)
                .build();

            Set<ConstraintViolation<TimeCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

}

