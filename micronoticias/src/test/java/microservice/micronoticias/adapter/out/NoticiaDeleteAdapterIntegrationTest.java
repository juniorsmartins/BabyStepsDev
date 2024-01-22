package microservice.micronoticias.adapter.out;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.port.output.NoticiaDeleteOutputPort;
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
@DisplayName("Integration Adapter - Delete Noticia")
class NoticiaDeleteAdapterIntegrationTest {

    @Autowired
    private NoticiaDeleteOutputPort noticiaDeleteOutputPort;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private EditoriaRepository editoriaRepository;

    @AfterEach
    void tearDown() {
        this.noticiaRepository.deleteAll();
        this.editoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("id válido")
    void dadoIdValido_QuandoDelete_EntaoApagarNoticia() {
        var noticiaEntity = this.noticiaRepository.findById(1001L);
        Assertions.assertFalse(noticiaEntity.isEmpty());

        this.noticiaDeleteOutputPort.deleteById(noticiaEntity.get().getId());

        var noticiaApagada = this.noticiaRepository.findById(noticiaEntity.get().getId());
        Assertions.assertTrue(noticiaApagada.isEmpty());
    }
}

