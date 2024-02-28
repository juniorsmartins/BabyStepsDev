package microservice.microinscricoes.adapter.out.unit;

import microservice.microinscricoes.adapter.out.InscricaoSaveAdapter;
import microservice.microinscricoes.adapter.out.repository.InscricaoRepository;
import microservice.microinscricoes.adapter.mapper.MapperOut;
import microservice.microinscricoes.utility.AbstractIntegrationTest;
import microservice.microinscricoes.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
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
@DisplayName("Unit - InscricaoOpenAdapter")
class InscricaoSaveAdapterTest extends AbstractIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @InjectMocks
    private InscricaoSaveAdapter inscricaoSaveAdapter;

    @Mock
    private InscricaoRepository inscricaoRepository;

    @Mock
    private MapperOut mapperOut;

    @Nested
    @DisplayName("Exceções")
    class TestesDeExcecao {

        @Test
        @DisplayName("nulo")
        void dadoInscricaoNula_QuandoOpen_EntaoLancarException() {
            Executable acao = () -> inscricaoSaveAdapter.save(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
            Mockito.verifyNoInteractions(mapperOut);
            Mockito.verifyNoInteractions(inscricaoRepository);
        }
    }
}

