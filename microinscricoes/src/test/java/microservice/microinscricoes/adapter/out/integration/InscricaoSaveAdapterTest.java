package microservice.microinscricoes.adapter.out.integration;

import microservice.microinscricoes.adapter.out.InscricaoSaveAdapter;
import microservice.microinscricoes.adapter.out.repository.InscricaoRepository;
import microservice.microinscricoes.adapter.out.repository.TorneioRepository;
import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.utility.AbstractIntegrationTest;
import microservice.microinscricoes.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration - InscricaoOpenAdapter")
class InscricaoSaveAdapterTest extends AbstractIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private InscricaoSaveAdapter inscricaoSaveAdapter;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private TorneioRepository torneioRepository;

    private Inscricao inscricao;

    @BeforeEach
    void setUp() {
        this.inscricao = this.factory.gerarInscricao();

        var torneioEntity = this.factory.gerarTorneioEntityBuilder()
            .id(1L)
            .build();
        this.torneioRepository.save(torneioEntity);
    }

    @AfterEach
    void tearDown() {
        this.inscricaoRepository.deleteAll();
        this.torneioRepository.deleteAll();
    }

    @Nested
    @DisplayName("save")
    class Save {

        @Test
        @DisplayName("padrão")
        void dadoInscricaoValida_QuandoSave_EntaoRetornarInscricaoSalva() {

            var response = inscricaoSaveAdapter.save(inscricao);
            Assertions.assertNotNull(response.getId());
            Assertions.assertNotNull(response.getTorneio());
        }
    }
}

