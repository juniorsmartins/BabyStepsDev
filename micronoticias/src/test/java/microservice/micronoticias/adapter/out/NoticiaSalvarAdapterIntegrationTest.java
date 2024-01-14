package microservice.micronoticias.adapter.out;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.domain.Editoria;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.application.port.output.NoticiaSalvarOutputPort;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = {"/sql/editorias/editorias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration Adapter - Notícia")
class NoticiaSalvarAdapterIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private NoticiaSalvarOutputPort noticiaSalvarOutputPort;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private EditoriaRepository editoriaRepository;

    // fixture ou Scaffolding
    private Noticia noticia;

    @BeforeEach
    void setUp() {
        noticia = factory.gerarNoticia(30, 150, 250, 400, 5000, 1, 50, 1, 100);
    }

    @AfterEach
    void tearDown() {
        this.noticiaRepository.deleteAll();
        this.editoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("duas existentes editorias")
    void dadoNoticiaValidaComDuasExistentesEditorias_QuandoSalvar_EntaoRetornarComAmbasSalvas() {
        var editoria1 = editoriaRepository.findById(1001L).get();
        var editoria2 = editoriaRepository.findById(1002L).get();

        Assertions.assertEquals("Economia", editoria1.getNomenclatura());
        Assertions.assertEquals("Tecnologia", editoria2.getNomenclatura());

        var editoriaAtual1 = factory.gerarEditoria();
        editoriaAtual1.setId(1001L);
        var editoriaAtual2 = factory.gerarEditoria();
        editoriaAtual2.setId(1002L);
        noticia.setEditorias(Set.of(editoriaAtual1, editoriaAtual2));

        var noticiaSalva = noticiaSalvarOutputPort.salvar(noticia);

        var nomenclaturas = noticiaSalva.getEditorias()
            .stream()
            .map(Editoria::getNomenclatura)
            .toList();

        Assertions.assertEquals(2, noticiaSalva.getEditorias().size());
        Assertions.assertFalse(nomenclaturas.contains(editoria1.getNomenclatura()));
        Assertions.assertFalse(nomenclaturas.contains(editoria2.getNomenclatura()));
        Assertions.assertTrue(nomenclaturas.contains(editoriaAtual1.getNomenclatura()));
        Assertions.assertTrue(nomenclaturas.contains(editoriaAtual2.getNomenclatura()));
    }
}

