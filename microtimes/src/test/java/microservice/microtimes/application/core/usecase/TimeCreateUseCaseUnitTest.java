package microservice.microtimes.application.core.usecase;

import microservice.microtimes.adapter.out.TimeSaveAdapter;
import microservice.microtimes.utility.AbstractTestcontainersTest;
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

import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - TimeCreateUseCase")
class TimeCreateUseCaseUnitTest extends AbstractTestcontainersTest {

    @Mock
    private TimeSaveAdapter timeSaveAdapter;

    @InjectMocks
    private TimeCreateUseCase timeCreateUseCase;

    @Test
    @DisplayName("nulo")
    void dadoTimeNulo_quandoCreate_entaoLancarException() {
        Executable acao = () -> this.timeCreateUseCase.create(null);
        Assertions.assertThrows(NoSuchElementException.class, acao);
        Mockito.verifyNoInteractions(this.timeSaveAdapter);
    }
}

