package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.port.output.NoticiaUpdateOutputPort;
import microservice.micronoticias.config.exception.http_500.NoticiaUpdateUseCaseException;
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
@DisplayName("Unit UseCase - Update Notícia")
class NoticiaUpdateUseCaseUnitTest {

    @Mock
    private NoticiaUpdateOutputPort noticiaUpdateOutputPort;

    @InjectMocks
    private NoticiaUpdateUseCase noticiaUpdateUseCase;

    @Test
    @DisplayName("nulo")
    void dadoNoticiaNula_QuandoUpdate_EntaoLancarException() {
        Executable acao = () -> this.noticiaUpdateUseCase.update(null);
        Assertions.assertThrows(NoticiaUpdateUseCaseException.class, acao);
        Mockito.verifyNoInteractions(noticiaUpdateOutputPort);
    }
}

