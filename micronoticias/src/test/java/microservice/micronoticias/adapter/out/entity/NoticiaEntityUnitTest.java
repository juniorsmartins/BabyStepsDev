package microservice.micronoticias.adapter.out.entity;

import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit Entity - Noticia")
class NoticiaEntityUnitTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    // fixture ou Scaffolding
    private NoticiaEntity noticiaEntity1;

    private NoticiaEntity noticiaEntity2;

    @BeforeEach
    void setUp() {
        var autorias = factory.gerarListString(1, 100);
        var fontes = factory.gerarListString(1, 250);

        noticiaEntity1 = factory.gerarNoticiaEntityBuilder(30, 150, 250, 400, 5000)
            .id(1L)
            .autorias(autorias)
            .fontes(fontes)
            .build();

        noticiaEntity2 = factory.gerarNoticiaEntityBuilder(30, 150, 250, 400, 5000)
            .id(2L)
            .autorias(autorias)
            .fontes(fontes)
            .build();
    }

    @Test
    @DisplayName("ids diferentes")
    void dadoNoticiasEntityValidasComIdsDiferentes_QuandoUsarEquals_RetornarFalse() {
        var noticiasIguais = noticiaEntity1.equals(noticiaEntity2);
        Assertions.assertFalse(noticiasIguais);
    }
}

