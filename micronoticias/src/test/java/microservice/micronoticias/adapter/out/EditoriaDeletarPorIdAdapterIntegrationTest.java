package microservice.micronoticias.adapter.out;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.config.exception.http_404.EditoriaNaoEncontradaException;
import microservice.micronoticias.config.exception.http_500.ProhibitedNullValueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
@DisplayName("Integration Adapter - Deletar Editoria")
class EditoriaDeletarPorIdAdapterIntegrationTest {

    @Autowired
    private EditoriaDeletarPorIdAdapter editoriaDeletarPorIdAdapter;

    @Autowired
    private EditoriaRepository editoriaRepository;

    @AfterEach
    void tearDown() {
        this.editoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("listar duas editorias")
    void dadoDuasEditoriasNoDatabase_QuandoListar_EntaoRetornarListaComDuasEditorias() {
        var editoriaEntity = this.editoriaRepository.findById(1001L);
        Assertions.assertFalse(editoriaEntity.isEmpty());

        this.editoriaDeletarPorIdAdapter.deletarPorId(editoriaEntity.get().getId());

        var editoriaApagado = this.editoriaRepository.findById(editoriaEntity.get().getId());
        Assertions.assertTrue(editoriaApagado.isEmpty());
    }

    @Test
    @DisplayName("id não encontrado")
    void dadoIdInexistente_QuandoDeletarPorId_EntaoLancarException() {
        var idInexistente = 0L;
        Executable acao = () -> this.editoriaDeletarPorIdAdapter.deletarPorId(idInexistente);
        Assertions.assertThrows(EditoriaNaoEncontradaException.class, acao);
    }

    @Test
    @DisplayName("null")
    void dadoIdNulo_QuandoDeletarPorId_EntaoLancarException() {
        Executable acao = () -> this.editoriaDeletarPorIdAdapter.deletarPorId(null);
        Assertions.assertThrows(ProhibitedNullValueException.class, acao);
    }
}

