package microservice.micronoticias.application.core.usecase.regras;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.config.exception.http_409.NomenclaturaNaoUnicaException;
import microservice.micronoticias.config.exception.http_409.RuleWithProhibitedNullValueException;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = {"/sql/editorias/editorias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration Rules - Editoria")
class SingleNamingRuleForEditorialIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private SingleNamingRuleForEditorial singleNamingRuleForEditorial;

    @Autowired
    private EditoriaRepository editoriaRepository;

    private EditoriaEntity editoriaEntity;

    @BeforeEach
    void setUp() {
        editoriaEntity = editoriaRepository.findById(1001L).orElseThrow();
    }

    @AfterEach
    void tearDown() {
        this.editoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("nomenclatura repetida")
    void dadoNoticiaValidaComNomenclaturaRepetida_QuandoUpdate_EntaoLancarException() {
        var editoria = factory.gerarEditoria();
        editoria.setId(1002L);
        editoria.setNomenclatura(editoriaEntity.getNomenclatura());

        Executable acao = () -> singleNamingRuleForEditorial.executar(editoria);
        Assertions.assertThrows(NomenclaturaNaoUnicaException.class, acao);
    }

    @Test
    @DisplayName("nulo")
    void dadoEditoriaNula_QuandoUpdate_EntaoLancarException() {
        Executable acao = () -> this.singleNamingRuleForEditorial.executar(null);
        Assertions.assertThrows(RuleWithProhibitedNullValueException.class, acao);
    }
}

