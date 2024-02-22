package microservice.microtimes.adapter.out;

import microservice.microtimes.adapter.out.mapper.MapperOut;
import microservice.microtimes.adapter.out.repository.TimeRepository;
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
@DisplayName("Unit - TimeSaveAdapter")
class TimeSaveAdapterTest extends AbstractTestcontainersTest {

    @Mock
    private TimeRepository timeRepository;

    @Mock
    private MapperOut mapperOut;

    @InjectMocks
    private TimeSaveAdapter timeSaveAdapter;

    @Test
    @DisplayName("nulo")
    void dadoTimeNulo_quandoSave_entaoLancarException() {
        Executable acao = () -> this.timeSaveAdapter.save(null);
        Assertions.assertThrows(NoSuchElementException.class, acao);
        Mockito.verifyNoInteractions(this.timeRepository);
        Mockito.verifyNoInteractions(this.mapperOut);
    }
}

