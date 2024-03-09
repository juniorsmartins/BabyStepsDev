package microservice.microtorneios.adapters.in.controller.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import microservice.microtorneios.adapters.in.controller.dto.TimeIdDto;
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
import utility.AbstractTestcontainersTest;
import utility.FactoryObjectMother;

import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - TorneioCreateDtoRequest")
class TorneioCreateDtoRequestUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private Validator validator;

    @Nested
    @DisplayName("Nome")
    class Nome {

        @Test
        @DisplayName("nulo")
        void dadoNomeNulo_quandoInstanciar_entaoLancarException() {
            var dtoRequest = factory.gerarTorneioCreateDtoRequestBuilder()
                .nome(null)
                .build();

            Set<ConstraintViolation<TorneioCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoNomeVazioOuEmBranco_quandoInstanciar_entaoLancarException(String valor) {
            var dtoRequest = factory.gerarTorneioCreateDtoRequestBuilder()
                .nome(valor)
                .build();

            Set<ConstraintViolation<TorneioCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Ano")
    class Ano {

        @Test
        @DisplayName("nulo")
        void dadoAnoNulo_quandoInstanciar_entaoLancarException() {
            var dtoRequest = factory.gerarTorneioCreateDtoRequestBuilder()
                .ano(null)
                .build();

            Set<ConstraintViolation<TorneioCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, 0})
        @DisplayName("negativo ou zero")
        void dadoAnoNegativoOuZero_quandoInstanciar_entaoLancarException(int valor) {
            var dtoRequest = factory.gerarTorneioCreateDtoRequestBuilder()
                .ano(valor)
                .build();

            Set<ConstraintViolation<TorneioCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Times")
    class Times {

        @Test
        @DisplayName("id nulo")
        void dadoTimeComIdNulo_quandoInstanciar_entaoLancarException() {
        var dtoRequest = factory.gerarTorneioCreateDtoRequestBuilder()
            .times(Set.of(new TimeIdDto(null)))
            .build();

            Set<ConstraintViolation<TorneioCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(longs = {-1, 0})
        @DisplayName("negativo ou zero")
        void dadoTimeComIdNegativoOuZero_quandoInstanciar_entaoLancarException(long valor) {
            var dtoRequest = factory.gerarTorneioCreateDtoRequestBuilder()
                .times(Set.of(new TimeIdDto(valor)))
                .build();

            Set<ConstraintViolation<TorneioCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }
}

