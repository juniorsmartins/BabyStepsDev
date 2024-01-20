package microservice.micronoticias.adapter.out;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = {"/sql/editorias/editorias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration Adapter - Listar Editoria")
class EditoriaListarAdapterIntegrationTest {

    @Autowired
    private EditoriaListarAdapter editoriaListarAdapter;

    @Autowired
    private EditoriaRepository editoriaRepository;

    @AfterEach
    void tearDown() {
        this.editoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("listar duas editorias")
    void dadoDuasEditoriasNoDatabase_QuandoListar_EntaoRetornarListaComDuasEditorias() {
        var resposta = this.editoriaListarAdapter.listar();
        Assertions.assertEquals(2, resposta.size());
    }
}

