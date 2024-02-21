package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.adapter.out.InscricaoSaveAdapter;
import microservice.microinscricoes.adapter.out.TorneioFindByIdAdapter;
import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.core.domain.Torneio;
import microservice.microinscricoes.application.port.output.InscricaoSaveOutputPort;
import microservice.microinscricoes.application.port.output.TorneioFindByIdOutputPort;
import microservice.microinscricoes.utility.AbstractIntegrationTest;
import microservice.microinscricoes.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - InscricaoOpenUseCase")
class InscricaoOpenUseCaseTest extends AbstractIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @InjectMocks
    private InscricaoCreateUseCase inscricaoOpenUseCase;

    private InscricaoSaveOutputPort inscricaoSaveOutputPort;

    private TorneioFindByIdOutputPort torneioFindByIdOutputPort;

    private Inscricao inscricao;

    private Torneio torneio;

    @BeforeEach
    void setUp() {
        this.inscricaoSaveOutputPort = Mockito.mock(InscricaoSaveAdapter.class);
        this.torneioFindByIdOutputPort = Mockito.mock(TorneioFindByIdAdapter.class);
        this.inscricaoOpenUseCase = new InscricaoCreateUseCase(inscricaoSaveOutputPort, torneioFindByIdOutputPort);

        this.inscricao = this.factory.gerarInscricao();
        this.torneio = inscricao.getTorneio();
    }

    @Nested
    @DisplayName("Exceções")
    class TestesDeExcecao {

        @Test
        @DisplayName("nulo")
        void dadoInscricaoNula_QuandoOpen_EntaoLancarException() {
            Executable acao = () -> inscricaoOpenUseCase.create(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
            Mockito.verifyNoInteractions(torneioFindByIdOutputPort);
            Mockito.verifyNoInteractions(inscricaoSaveOutputPort);
        }
    }


    @Nested
    @DisplayName("Dados válidos")
    class TestesValidos {

        @Test
        @DisplayName("padrão")
        void dadoInscricaoValida_QuandoOpen_EntaoRetornarInscricaoSalva() {
            inscricao.setId(1L);

            Mockito.when(torneioFindByIdOutputPort.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(torneio));

            Mockito.when(inscricaoSaveOutputPort.save(Mockito.any()))
                .thenReturn(inscricao);

            var result = inscricaoOpenUseCase.create(inscricao);

            Assertions.assertNotNull(result.getId());
        }
    }
}

