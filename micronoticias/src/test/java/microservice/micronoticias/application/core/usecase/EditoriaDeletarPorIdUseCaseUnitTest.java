package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.adapter.out.EditoriaDeletarPorIdAdapter;
import microservice.micronoticias.config.exception.http_500.EditoriaDeletarPorIdUseCaseException;
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
@DisplayName("Unit UseCase - DeletarPorId Editoria")
class EditoriaDeletarPorIdUseCaseUnitTest {

    @Mock
    private EditoriaDeletarPorIdAdapter editoriaDeletarPorIdAdapter;

    @InjectMocks
    private EditoriaDeletarPorIdUseCase editoriaDeletarPorIdUseCase;

    @Test
    @DisplayName("nulo")
    void dadoIdNulo_QuandoDeletarEditoriaPorId_EntaoLancarException() {
        Executable acao = () -> this.editoriaDeletarPorIdUseCase.deletarPorId(null);
        Assertions.assertThrows(EditoriaDeletarPorIdUseCaseException.class, acao);
        Mockito.verifyNoInteractions(editoriaDeletarPorIdAdapter);
    }
}

