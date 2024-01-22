package microservice.micronoticias.adapter.out;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.port.output.EditoriaInUseOutputPort;
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
@Sql(scripts = {"/sql/editorias/editorias-insert.sql", "/sql/noticias/noticias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration Adapter - InUse Editoria")
class EditoriaInUseAdapterIntegrationTest {

    @Autowired
    private EditoriaInUseOutputPort editoriaInUseOutputPort;

    @Autowired
    private EditoriaRepository editoriaRepository;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @AfterEach
    void tearDown() {
        this.noticiaRepository.deleteAll();
        this.editoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("em uso true")
    void dadoEditoriaEmUsoTrue_QuandoInUse_EntaoLancarException() {
        var response = this.editoriaInUseOutputPort.inUse(1001L);
        Assertions.assertTrue(response);
    }

    @Test
    @DisplayName("em uso false")
    void dadoEditoriaEmUsoFalse_QuandoInUse_EntaoLancarException() {
        var response = this.editoriaInUseOutputPort.inUse(1002L);
        Assertions.assertFalse(response);
    }
}

