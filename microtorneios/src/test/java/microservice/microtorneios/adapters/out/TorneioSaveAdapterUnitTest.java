package microservice.microtorneios.adapters.out;

import microservice.microtorneios.adapters.mapper.MapperOut;
import microservice.microtorneios.adapters.out.repository.TimeRepository;
import microservice.microtorneios.adapters.out.repository.TorneioRepository;
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
@DisplayName("Unit - TorneioSaveAdapter")
class TorneioSaveAdapterUnitTest {

    @Mock
    private TorneioRepository torneioRepository;

    @Mock
    private TimeRepository timeRepository;

    @Mock
    private MapperOut mapperOut;

    @InjectMocks
    private TorneioSaveAdapter torneioSaveAdapter;

    @Test
    @DisplayName("nulo")
    void dadoTorneioNulo_quandoSave_entaoLancarException() {
        Executable acao = () -> this.torneioSaveAdapter.save(null);
        Assertions.assertThrows(NoSuchElementException.class, acao);
        Mockito.verifyNoInteractions(this.torneioRepository);
        Mockito.verifyNoInteractions(this.timeRepository);
        Mockito.verifyNoInteractions(this.mapperOut);
    }
}

