package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.config.exception.http_409.NomenclaturaNaoUnicaException;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = {"/sql/editorias/editorias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration UseCase - Criar Editoria")
class EditoriaCriarUseCaseIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private EditoriaCriarUseCase editoriaCriarUseCase;

    @Autowired
    private EditoriaRepository editoriaRepository;

    private EditoriaEntity editoriaEntity;

    @BeforeEach
    void setUp() {
        editoriaEntity = editoriaRepository.findById(1001L).orElseThrow();
    }

    @AfterEach
    void tearDown() {
        this.editoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("nomenclatura repetida")
    void dadoNoticiaValidaComNomenclaturaRepetida_QuandoCriar_EntaoLancarException() {
        var editoria = factory.gerarEditoria();
        editoria.setNomenclatura(editoriaEntity.getNomenclatura());

        Executable acao = () -> this.editoriaCriarUseCase.criar(editoria);
        Assertions.assertThrows(NomenclaturaNaoUnicaException.class, acao);
    }
}

