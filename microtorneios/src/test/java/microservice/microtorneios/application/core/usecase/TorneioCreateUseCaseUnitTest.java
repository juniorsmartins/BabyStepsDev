package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.adapters.out.TorneioSaveAdapter;
import microservice.microtorneios.application.port.output.CarteiroNotifyCreatedTorneioOutputPort;
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
import utility.AbstractTestcontainersTest;

import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - TorneioCreateUseCase")
class TorneioCreateUseCaseUnitTest extends AbstractTestcontainersTest {

    @Mock
    private TorneioSaveAdapter torneioSaveAdapter;

    @Mock
    private CarteiroNotifyCreatedTorneioOutputPort notifyCreationOfNewTorneioOutputPort;

    @InjectMocks
    private TorneioCreateUseCase torneioCreateUseCase;

    @Test
    @DisplayName("nulo")
    void dadoTorneioNulo_quandoCreate_entaoLancarException() {
        Executable acao = () -> this.torneioCreateUseCase.create(null);
        Assertions.assertThrows(NoSuchElementException.class, acao);
        Mockito.verifyNoInteractions(this.torneioSaveAdapter);
        Mockito.verifyNoInteractions(this.notifyCreationOfNewTorneioOutputPort);
    }
}

