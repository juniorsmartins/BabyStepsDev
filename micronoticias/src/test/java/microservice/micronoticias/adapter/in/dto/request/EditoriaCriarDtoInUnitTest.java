package microservice.micronoticias.adapter.in.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
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

    }
}

