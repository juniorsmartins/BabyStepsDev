package microservice.micronoticias.adapter.out.repository;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

import java.util.Set;

@DataJpaTest
@Sql(scripts = {"/sql/editorias/editorias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration Repository - Notícia")
class NoticiaRepositoryIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private EditoriaRepository editoriaRepository;

    @Nested
    @DisplayName("Save")
    class SaveNoticia {

        NoticiaEntity.NoticiaEntityBuilder noticiaEntityBuilder;

        @BeforeEach
        void setUp() {
            var autorias = factory.gerarListString(1, 100);
            var fontes = factory.gerarListString(1, 250);

            noticiaEntityBuilder = factory.gerarNoticiaEntityBuilder(30, 150, 250, 400, 5000)
                .autorias(autorias)
                .fontes(fontes);
        }

        @Test
        @DisplayName("dados completos")
        void dadaNoticiaValida_QuandoSalvar_EntaoRetornarDadosCompletosSalvos() {
            var noticia = noticiaEntityBuilder.build();
            var noticiaSalva = noticiaRepository.save(noticia);

            Assertions.assertTrue(noticiaSalva.getId() > 0);
            Assertions.assertEquals(noticia.getChapeu(), noticiaSalva.getChapeu());
            Assertions.assertEquals(noticia.getTitulo(), noticiaSalva.getTitulo());
            Assertions.assertEquals(noticia.getLinhaFina(), noticiaSalva.getLinhaFina());
            Assertions.assertEquals(noticia.getLide(), noticiaSalva.getLide());
            Assertions.assertEquals(noticia.getCorpo(), noticiaSalva.getCorpo());
            Assertions.assertEquals(1, noticiaSalva.getAutorias().size());
            Assertions.assertEquals(1, noticiaSalva.getFontes().size());
            Assertions.assertEquals(1, noticiaSalva.getEditorias().size());
        }

        @Test
        @DisplayName("listas bem populadas")
        void dadoNoticiaValidaComListasBemPopuladas_QuandoSalvar_EntaoRetornarListasSalvas() {
            var autorias = factory.gerarListString(3, 100);
            var fontes = factory.gerarListString(5, 250);

            var noticia = noticiaEntityBuilder.autorias(autorias)
                .fontes(fontes)
                .build();

            var noticiaSalva = noticiaRepository.save(noticia);
            Assertions.assertEquals(3, noticiaSalva.getAutorias().size());
            Assertions.assertEquals(5, noticiaSalva.getFontes().size());
        }

        @Test
        @DisplayName("duas novas editorias")
        void dadoNoticiaValidaComDuasNovasEditorias_QuandoSalvar_EntaoRetornarComAmbasSalvas() {
            var novaEditoria1 = factory.gerarEditoriaEntityBuilder().build();
            var novaEditoria2 = factory.gerarEditoriaEntityBuilder().build();

            var noticia = noticiaEntityBuilder.editorias(Set.of(novaEditoria1, novaEditoria2))
                .build();

            var noticiaSalva = noticiaRepository.save(noticia);
            Assertions.assertEquals(2, noticiaSalva.getEditorias().size());
        }

        @Test
        @DisplayName("duas existentes editorias")
        void dadoNoticiaValidaComDuasExistentesEditorias_QuandoSalvar_EntaoRetornarComAmbasSalvas() {
            var editoria1 = editoriaRepository.findById(1001L).get();
            var editoria2 = editoriaRepository.findById(1002L).get();

            Assertions.assertEquals("Economia", editoria1.getNomenclatura());
            Assertions.assertEquals("Tecnologia", editoria2.getNomenclatura());

            var novaEditoria1 = factory.gerarEditoriaEntityBuilder()
                .id(1001L)
                .build();
            var novaEditoria2 = factory.gerarEditoriaEntityBuilder()
                .id(1002L)
                .build();

            var noticia = noticiaEntityBuilder.editorias(Set.of(novaEditoria1, novaEditoria2))
                .build();

            var noticiaSalva = noticiaRepository.save(noticia);
            var nomenclaturas = noticiaSalva.getEditorias()
                .stream()
                .map(EditoriaEntity::getNomenclatura)
                .toList();

            Assertions.assertEquals(2, noticiaSalva.getEditorias().size());
            Assertions.assertFalse(nomenclaturas.contains(editoria1.getNomenclatura()));
            Assertions.assertFalse(nomenclaturas.contains(editoria2.getNomenclatura()));
            Assertions.assertTrue(nomenclaturas.contains(novaEditoria1.getNomenclatura()));
            Assertions.assertTrue(nomenclaturas.contains(novaEditoria2.getNomenclatura()));
        }
    }
}

