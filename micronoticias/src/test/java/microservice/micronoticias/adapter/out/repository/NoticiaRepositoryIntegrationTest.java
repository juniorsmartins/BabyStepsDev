package microservice.micronoticias.adapter.out.repository;

import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Integration Repository - Notícia")
class NoticiaRepositoryIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Nested
    @DisplayName("Save")
    class SaveNoticia {

        NoticiaEntity noticiaEntity;

        @BeforeEach
        void criarCenario() {
            var autorias = factory.gerarListString(1, 100);
            var fontes = factory.gerarListString(1, 250);

            noticiaEntity = factory.gerarNoticiaEntityBuilder(30, 150, 250, 400, 5000)
                .autorias(autorias)
                .fontes(fontes)
                .build();
        }

        @Test
        @DisplayName("dados completos")
        void dadaNoticiaValida_QuandoSalvar_EntaoRetornarDadosCompletosSalvos() {
            var noticiaSalva = noticiaRepository.save(noticiaEntity);
            Assertions.assertTrue(noticiaSalva.getId() > 0);
            Assertions.assertEquals(30, noticiaSalva.getChapeu().length());
            Assertions.assertEquals(150, noticiaSalva.getTitulo().length());
            Assertions.assertEquals(250, noticiaSalva.getLinhaFina().length());
            Assertions.assertEquals(400, noticiaSalva.getLide().length());
            Assertions.assertEquals(5000, noticiaSalva.getCorpo().length());
            Assertions.assertEquals(1, noticiaSalva.getAutorias().size());
            Assertions.assertEquals(1, noticiaSalva.getFontes().size());
        }

        @Test
        @DisplayName("listas populadas")
        void dadoNoticiaValidaComListasBemPopuladas_QuandoSalvar_EntaoRetornarListasSalvas() {
            var autorias = factory.gerarListString(3, 100);
            var fontes = factory.gerarListString(5, 250);
            noticiaEntity.setAutorias(autorias);
            noticiaEntity.setFontes(fontes);

            var noticiaSalva = noticiaRepository.save(noticiaEntity);
            Assertions.assertEquals(3, noticiaSalva.getAutorias().size());
            Assertions.assertEquals(5, noticiaSalva.getFontes().size());
        }
    }
}

