package microservice.micronoticias.adapter.out;

import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.config.exception.http_404.NoticiaNotFoundException;
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
@DisplayName("Unit Adapter - Delete Editoria")
class NoticiaDeleteAdapterUnitTest {

    @Mock
    private NoticiaRepository noticiaRepository;

    @InjectMocks
    private NoticiaDeleteAdapter noticiaDeleteAdapter;

    @Test
    @DisplayName("id não encontrado")
    void dadoIdInexistente_QuandoDelete_EntaoLancarException() {
        var idInexistente = 0L;
        Executable acao = () -> this.noticiaDeleteAdapter.deleteById(idInexistente);
        Assertions.assertThrows(NoticiaNotFoundException.class, acao);
        Mockito.verifyNoInteractions(noticiaRepository);
    }

    @Test
    @DisplayName("null")
    void dadoIdNulo_QuandoDelete_EntaoLancarException() {
        Executable acao = () -> this.noticiaDeleteAdapter.deleteById(null);
        Assertions.assertThrows(ProhibitedNullValueException.class, acao);
        Mockito.verifyNoInteractions(noticiaRepository);
    }
}

