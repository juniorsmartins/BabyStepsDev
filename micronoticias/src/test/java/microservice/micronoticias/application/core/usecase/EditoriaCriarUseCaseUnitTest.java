package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.adapter.out.EditoriaSalvarAdapter;
import microservice.micronoticias.config.exception.http_500.EditoriaCriarUseCaseException;
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
@DisplayName("Unit UseCase - Criar Editoria")
class EditoriaCriarUseCaseUnitTest {

    @Mock
    private EditoriaSalvarAdapter editoriaSalvarAdapter;

    @InjectMocks
    private EditoriaCriarUseCase editoriaCriarUseCase;

    @Test
    @DisplayName("nulo")
    void dadoEditoriaNula_QuandoCriar_EntaoLancarException() {
        Executable acao = () -> this.editoriaCriarUseCase.criar(null);
        Assertions.assertThrows(EditoriaCriarUseCaseException.class, acao);
        Mockito.verifyNoInteractions(editoriaSalvarAdapter);
    }
}

