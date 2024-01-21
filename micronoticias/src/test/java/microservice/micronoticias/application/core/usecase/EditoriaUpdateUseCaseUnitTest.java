package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.adapter.out.EditoriaUpdateAdapter;
import microservice.micronoticias.config.exception.http_500.EditoriaDeletarPorIdUseCaseException;
import microservice.micronoticias.config.exception.http_500.EditoriaUpdateUseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
@DisplayName("Unit UseCase - Update Editoria")
class EditoriaUpdateUseCaseUnitTest {

    @Mock
    private EditoriaUpdateAdapter editoriaUpdateAdapter;

    @InjectMocks
    private EditoriaUpdateUseCase editoriaUpdateUseCase;

    @Test
    @DisplayName("nulo")
    void dadoEditoriaNula_QuandoUpdate_EntaoLancarException() {
        Executable acao = () -> this.editoriaUpdateUseCase.update(null);
        Assertions.assertThrows(EditoriaUpdateUseCaseException.class, acao);
        Mockito.verifyNoInteractions(editoriaUpdateAdapter);
    }
}

