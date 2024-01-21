package microservice.micronoticias.adapter.out;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.port.output.EditoriaUpdateOutputPort;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = {"/sql/editorias/editorias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration Adapter - Update Editoria")
class EditoriaUpdateAdapterIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private EditoriaUpdateOutputPort editoriaUpdateOutputPort;

    @Autowired
    private EditoriaRepository editoriaRepository;

    private EditoriaEntity editoriaEntity;

    private Editoria editoria;

    @BeforeEach
    void setUp() {
        this.editoriaEntity = this.editoriaRepository.findById(1001L).orElseThrow();
        this.editoria = factory.gerarEditoria();
    }

    @AfterEach
    void tearDown() {
        this.editoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("dados válidos")
    void dadoEditoriaValida_QuandoUpdate_EntaoRetornarSalvo() {
        Assertions.assertEquals("Economia", editoriaEntity.getNomenclatura());
        Assertions.assertEquals("Informações sobre a economia do país e mundo.", editoriaEntity.getDescricao());

        editoria.setId(editoriaEntity.getId());
        this.editoriaUpdateOutputPort.update(editoria);
        var editoriaUpdate = this.editoriaRepository.findById(editoriaEntity.getId()).orElseThrow();

        Assertions.assertNotEquals("Economia", editoriaUpdate.getNomenclatura());
        Assertions.assertNotEquals("Informações sobre a economia do país e mundo.", editoriaUpdate.getDescricao());
    }
}

