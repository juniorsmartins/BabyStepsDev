package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.config.exception.http_409.NomenclaturaNaoUnicaException;
import microservice.micronoticias.config.exception.http_409.RuleWithProhibitedNullValueException;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration Rules - Notícia")
class RuleNomenclaturaUnicaDeEditoriaIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private RuleNomenclaturaUnicaDeEditoria ruleNomenclaturaUnicaDeEditoria;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private EditoriaRepository editoriaRepository;

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
    @DisplayName("null")
    void dadoNoticiaNula_QuandoExecutar_EntaoLancarException() {
        Executable acao = () -> ruleNomenclaturaUnicaDeEditoria.executar(null);
        Assertions.assertThrows(RuleWithProhibitedNullValueException.class, acao);
    }

    @Test
    @DisplayName("noticia com nomenclatura repetida de editoria")
    void dadoNoticiaValidaComEditoriaComNomenclaturaRepetida_QuandoExecutar_EntaoLancarException() {
        var editorias = noticiaEntity.getEditorias();
        var editoriaComNomenclaturaRepetida = factory.gerarEditoria();
        editorias.forEach(editoria -> editoriaComNomenclaturaRepetida.setNomenclatura(editoria.getNomenclatura()));

        var noticia = factory.gerarNoticia(30, 150, 250, 400, 5000, 1, 50, 1, 100);
        noticia.setEditorias(Set.of(editoriaComNomenclaturaRepetida));

        Executable acao = () -> ruleNomenclaturaUnicaDeEditoria.executar(noticia);
        Assertions.assertThrows(NomenclaturaNaoUnicaException.class, acao);
    }
}

