package microservice.micronoticias.application.core.usecase;

import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.config.exception.http_409.NomenclaturaNaoUnicaException;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = {"/sql/editorias/editorias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration UseCase - Notícia")
class NoticiaCriarUseCaseIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private NoticiaCriarUseCase noticiaCriarUseCase;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private EditoriaRepository editoriaRepository;

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
    void dadoNoticiaValidaComEditoriaComNomeNaoUnico_QuandoCriarNoticia_EntaoLancarException() {
        var editoriaExistente = this.editoriaRepository.findById(1001L).get();

        var editoria1 = factory.gerarEditoria();
        var editoria2 = factory.gerarEditoria();
        editoria2.setNomenclatura(editoriaExistente.getNomenclatura());

        noticia.setEditorias(Set.of(editoria1, editoria2));

        Executable acao = () -> this.noticiaCriarUseCase.criar(noticia);
        Assertions.assertThrows(NomenclaturaNaoUnicaException.class, acao);
    }
}