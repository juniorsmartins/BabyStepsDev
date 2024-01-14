package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.config.exception.http_409.NoticiaRepetidaException;
import microservice.micronoticias.config.exception.http_409.RuleWithProhibitedNullValueException;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration Rules - Notícia")
class RuleNoticiaUnicaIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private RuleNoticiaUnicaToCreateNews ruleNoticiaUnica;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private EditoriaRepository editoriaRepository;

    // fixture ou Scaffolding
    private NoticiaEntity noticiaEntity;

    @BeforeEach
    void setUp() {
        var autorias = factory.gerarListString(2, 100);
        var fontes = factory.gerarListString(2, 250);

        noticiaEntity = factory.gerarNoticiaEntityBuilder(30, 150, 250, 400, 5000)
            .autorias(autorias)
            .fontes(fontes)
            .build();

        this.noticiaRepository.save(noticiaEntity);
    }

    @AfterEach
    void tearDown() {
        this.noticiaRepository.deleteAll();
        this.editoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("noticia repetida")
    void dadoNoticiaValidaRepetida_QuandoExecutar_EntaoLancarException() {
        var noticia = factory.gerarNoticia(30, 150, 250, 400, 5000, 1, 50, 1, 100);
        noticia.setChapeu(noticiaEntity.getChapeu());
        noticia.setTitulo(noticiaEntity.getTitulo());
        noticia.setLinhaFina(noticiaEntity.getLinhaFina());

        Executable acao = () -> ruleNoticiaUnica.executar(noticia);
        Assertions.assertThrows(NoticiaRepetidaException.class, acao);
    }

    @Test
    @DisplayName("notícia nula")
    void dadoNoticiaNula_QuandoExecutar_EntaoLancarException() {
        Executable acao = () -> ruleNoticiaUnica.executar(null);
        Assertions.assertThrows(RuleWithProhibitedNullValueException.class, acao);
    }
}

