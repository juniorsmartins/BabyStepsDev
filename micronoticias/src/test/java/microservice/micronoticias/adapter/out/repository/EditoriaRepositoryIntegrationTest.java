package microservice.micronoticias.adapter.out.repository;

import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Integration Repository - Editoria")
class EditoriaRepositoryIntegrationTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private EditoriaRepository editoriaRepository;

    @Nested
    @DisplayName("Save")
    class SaveEditoria {

        EditoriaEntity.EditoriaEntityBuilder editoriaEntityBuilder;

        @BeforeEach
        void setUp() {
            editoriaEntityBuilder = factory.gerarEditoriaEntityBuilder();
        }

        @Test
        @DisplayName("dados completos")
        void dadaEditoriaValida_QuandoSalvar_EntaoRetornarDadosCompletosSalvos() {
            var editoria = editoriaEntityBuilder.build();
            var editoriaSalva = editoriaRepository.save(editoria);

            Assertions.assertTrue(editoriaSalva.getId() > 0);
            Assertions.assertEquals(editoria.getNomenclatura(), editoriaSalva.getNomenclatura());
            Assertions.assertEquals(editoria.getDescricao(), editoriaSalva.getDescricao());
        }
    }
}

