package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.adapter.out.NoticiaDeleteAdapter;
import microservice.micronoticias.config.exception.http_500.NoticiaDeleteUseCaseException;
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
@DisplayName("Unit UseCase - Delete Notícia")
class NoticiaDeleteUseCaseUnitTest {

    @Mock
    private NoticiaDeleteAdapter noticiaDeleteAdapter;

    @InjectMocks
    private NoticiaDeleteUseCase noticiaDeleteUseCase;

    @Test
    @DisplayName("nulo")
    void dadoIdNulo_QuandoDelete_EntaoLancarException() {
        Executable acao = () -> this.noticiaDeleteUseCase.deleteById(null);
        Assertions.assertThrows(NoticiaDeleteUseCaseException.class, acao);
        Mockito.verifyNoInteractions(noticiaDeleteAdapter);
    }
}

