package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.application.port.output.NoticiaSalvarOutputPort;
import microservice.micronoticias.config.exception.http_500.NoticiaCriarUseCaseException;
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
@DisplayName("Unit UseCase - Criar Notícia")
class NoticiaCriarUseCaseUnitTest {

    @Mock
    private NoticiaSalvarOutputPort cadastrarOutputPort;

    @InjectMocks
    private NoticiaCriarUseCase noticiaCriarUseCase;

    @Test
    @DisplayName("nulo")
    void dadoEditoriaNula_QuandoCriar_EntaoLancarException() {
        Executable acao = () -> this.noticiaCriarUseCase.criar(null);
        Assertions.assertThrows(NoticiaCriarUseCaseException.class, acao);
        Mockito.verifyNoInteractions(cadastrarOutputPort);
    }
}

