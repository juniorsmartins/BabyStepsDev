package microservice.micronoticias.adapter.out;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.port.output.NoticiaUpdateOutputPort;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = {"/sql/editorias/editorias-insert.sql", "/sql/noticias/noticias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration Adapter - Update Notícia")
class NoticiaUpdateAdapterIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private NoticiaUpdateOutputPort noticiaUpdateOutputPort;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private EditoriaRepository editoriaRepository;

    private NoticiaEntity noticiaEntity;

    private EditoriaEntity editoriaEntity;

    @BeforeEach
    void setUp() {
        noticiaEntity = this.noticiaRepository.findById(1001L).orElseThrow();
        editoriaEntity = this.editoriaRepository.findById(1001L).orElseThrow();
    }

    @AfterEach
    void tearDown() {
        this.noticiaRepository.deleteAll();
        this.editoriaRepository.deleteAll();
    }

    @Nested
    @DisplayName("noticia")
    class UpdateNoticia {

        @Test
        @DisplayName("dados válidos")
        void dadoNoticiaValida_QuandoUpdateDaNoticia_EntaoRetornarNoticiaAtualizada() {
            Assertions.assertEquals("chapeu", noticiaEntity.getChapeu());
            Assertions.assertEquals("titulo xcvoiuto poidv", noticiaEntity.getTitulo());
            Assertions.assertEquals("linha_fina oploiuiopl oploiuiopl oploiuiopl oploiuiopl qwerqwe sdfgdghh qwerqwe sdfgd", noticiaEntity.getLinhaFina());
            Assertions.assertEquals("lide oploiuiopl oploiuiopl oploiuiopl oploiuiopl qwerqwe sdfgdghh qwerqwe sdfgdg", noticiaEntity.getLide());
            Assertions.assertEquals("corpo oploiuiopl oploiuiopl oploiuiopl oploiuiopl qwerqwe sdfgdghh qwerqwe sdfgdb hjkhjkhjk luiluolui", noticiaEntity.getCorpo());
            Assertions.assertEquals("Fonte 1", noticiaEntity.getFontes().get(0));
            Assertions.assertEquals("Autor 1", noticiaEntity.getAutorias().get(0));

            var editoriaDoBanco = noticiaEntity.getEditorias().stream().findFirst().orElseThrow();
            var editoria = factory.gerarEditoria();
            editoria.setId(editoriaDoBanco.getId());
            editoria.setNomenclatura(editoriaDoBanco.getNomenclatura());
            editoria.setDescricao(editoriaDoBanco.getDescricao());

            var noticia = factory.gerarNoticia(30, 150, 250, 400, 5000, 1, 50, 1, 100);
            noticia.setEditorias(Set.of(editoria));
            noticia.setId(noticiaEntity.getId());
            noticia.setChapeu("Chapéu atualizado");
            noticia.setTitulo("Título atualizado 213123123123123");
            noticia.setLinhaFina("LinhaFina atualizada flsdkfsldfk sdlfkjsldfkjsdf wpeojfspdifjsdif apidfjsdifjs df psdfjsdfi");
            noticia.setLide("Lide paosdfapd apsodfa spdf asdpasoijd a adijaspdoja vpemfsldfns alsdnfa,dnf a,snfa,sdmnf a,sdnal,sdn sflsienf");
            noticia.setCorpo("Corpo lkndfsoid osidfsodfn sdjfnsdfkjsd isldfnsldfn sdjf,sdfs, laisdfsaldi alsdknasldna alsdnasldansd alsdnalsdn");
            noticia.setAutorias(List.of("Autor 2"));
            noticia.setFontes(List.of("Fonte 2"));

            var noticiaSalva = noticiaUpdateOutputPort.update(noticia);

            Assertions.assertNotEquals("chapeu", noticiaSalva.getChapeu());
            Assertions.assertNotEquals("titulo xcvoiuto poidv", noticiaSalva.getTitulo());
            Assertions.assertNotEquals("linha_fina oploiuiopl oploiuiopl oploiuiopl oploiuiopl qwerqwe sdfgdghh qwerqwe sdfgd", noticiaSalva.getLinhaFina());
            Assertions.assertNotEquals("lide oploiuiopl oploiuiopl oploiuiopl oploiuiopl qwerqwe sdfgdghh qwerqwe sdfgdg", noticiaSalva.getLide());
            Assertions.assertNotEquals("corpo oploiuiopl oploiuiopl oploiuiopl oploiuiopl qwerqwe sdfgdghh qwerqwe sdfgdb hjkhjkhjk luiluolui", noticiaSalva.getCorpo());
            Assertions.assertNotEquals("Autor 1", noticiaSalva.getFontes().get(0));
            Assertions.assertNotEquals("Fonte 1", noticiaSalva.getAutorias().get(0));
        }
    }

    @Nested
    @DisplayName("editoria")
    class UpdateEditoria {

        @Test
        @DisplayName("dados válidos")
        void dadoNoticiaValida_QuandoUpdateDaEditoria_EntaoRetornarNoticiaComEditoriaAtualizada() {
            Assertions.assertEquals("Economia", editoriaEntity.getNomenclatura());
            Assertions.assertEquals("Informações sobre a economia do país e mundo.", editoriaEntity.getDescricao());

            var editoria = factory.gerarEditoria();
            editoria.setId(editoriaEntity.getId());
            editoria.setNomenclatura("Economia atualizada");
            editoria.setDescricao("Informações atualizadas sobre a economia do país e do mundo.");

            var noticia = factory.gerarNoticia(30, 150, 250, 400, 5000, 1, 50, 1, 100);
            noticia.setId(noticiaEntity.getId());
            noticia.setChapeu(noticiaEntity.getChapeu());
            noticia.setTitulo(noticiaEntity.getTitulo());
            noticia.setLinhaFina(noticiaEntity.getLinhaFina());
            noticia.setLide(noticiaEntity.getLide());
            noticia.setCorpo(noticiaEntity.getCorpo());
            noticia.setFontes(noticiaEntity.getFontes());
            noticia.setAutorias(noticiaEntity.getAutorias());
            noticia.setEditorias(Set.of(editoria));

            var noticiaSalva = noticiaUpdateOutputPort.update(noticia);
            var editoriaSalva = noticiaSalva.getEditorias().stream().findFirst().orElseThrow();

            Assertions.assertNotEquals("Economia", editoriaSalva.getNomenclatura());
            Assertions.assertNotEquals("Informações sobre a economia do país e mundo.", editoriaSalva.getDescricao());
        }
    }
}

