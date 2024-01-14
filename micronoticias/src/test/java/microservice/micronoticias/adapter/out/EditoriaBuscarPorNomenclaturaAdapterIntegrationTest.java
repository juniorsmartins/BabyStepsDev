package microservice.micronoticias.adapter.out;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
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
@DisplayName("Integration Adapter - Notícia")
class EditoriaBuscarPorNomenclaturaAdapterIntegrationTest {

    @Autowired
    private EditoriaBuscarPorNomenclaturaAdapter editoriaBuscarPorNomenclaturaAdapter;

    @Autowired
    private EditoriaRepository editoriaRepository;

    private EditoriaEntity editoriaEntity;

    @BeforeEach
    void setUp() {
        editoriaEntity = this.editoriaRepository.findById(1001L).orElseThrow();
    }

    @AfterEach
    void tearDown() {
        this.editoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("optional empty")
    void dadoEditoriaComNomenclaturaNaoExistente_QuandoBuscarPorNomenclatura_EntaoRetornarOptionalVazio() {
        var nomenclaturaInexistente = "Nomenclatura Inexistente";
        var resposta = this.editoriaBuscarPorNomenclaturaAdapter.buscarPorNomenclatura(nomenclaturaInexistente);
        Assertions.assertTrue(resposta.isEmpty());
    }

    @Test
    @DisplayName("optional full")
    void dadoEditoriaComNomenclaturaExistente_QuandoBuscarPorNomenclatura_EntaoRetornarOptionalComEditoria() {
        var nomenclaturaExistente = editoriaEntity.getNomenclatura();
        var resposta = this.editoriaBuscarPorNomenclaturaAdapter.buscarPorNomenclatura(nomenclaturaExistente);
        Assertions.assertFalse(resposta.isEmpty());
    }
}

