package microservice.micronoticias.adapter.out;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.config.exception.http_404.EditoriaNotFoundException;
import microservice.micronoticias.config.exception.http_500.ProhibitedNullValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit Adapter - Deletar Editoria")
class EditoriaDeletarPorIdAdapterUnitTest {

    @Mock
    private EditoriaRepository editoriaRepository;

    @InjectMocks
    private EditoriaDeletarPorIdAdapter editoriaDeletarPorIdAdapter;

    @Test
    @DisplayName("id não encontrado")
    void dadoIdInexistente_QuandoDeletarPorId_EntaoLancarException() {
        var idInexistente = 0L;
        Executable acao = () -> this.editoriaDeletarPorIdAdapter.deletarPorId(idInexistente);
        Assertions.assertThrows(EditoriaNotFoundException.class, acao);
        Mockito.verifyNoInteractions(editoriaRepository);
    }

    @Test
    @DisplayName("null")
    void dadoIdNulo_QuandoDeletarPorId_EntaoLancarException() {
        Executable acao = () -> this.editoriaDeletarPorIdAdapter.deletarPorId(null);
        Assertions.assertThrows(ProhibitedNullValueException.class, acao);
        Mockito.verifyNoInteractions(editoriaRepository);
    }
}

