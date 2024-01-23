package microservice.micronoticias.adapter.out;

import microservice.micronoticias.adapter.out.mapper.EditoriaMapperOut;
import microservice.micronoticias.adapter.out.mapper.NoticiaMapperOut;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.application.core.domain.Noticia;
import microservice.micronoticias.config.exception.http_404.NoticiaNotFoundException;
import microservice.micronoticias.config.exception.http_500.FailedToUpdateNewsException;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit Adapter - Update Notícia")
class NoticiaUpdateAdapterUnitTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Mock
    private NoticiaRepository noticiaRepository;

    @Mock
    private EditoriaRepository editoriaRepository;

    @Mock
    private NoticiaMapperOut noticiaMapperOut;

    @Mock
    private EditoriaMapperOut editoriaMapperOut;

    @InjectMocks
    private NoticiaUpdateAdapter noticiaUpdateAdapter;

    private Noticia noticia;

    @BeforeEach
    void setUp() {
        noticia = factory.gerarNoticia(30, 150, 250, 400, 5000, 1, 50, 1, 100);
    }

    @Test
    @DisplayName("nulo")
    void dadoNoticiaNula_QuandoUpdate_EntaoLancarException() {
        Executable acao = () -> this.noticiaUpdateAdapter.update(null);
        Assertions.assertThrows(FailedToUpdateNewsException.class, acao);
        Mockito.verifyNoInteractions(noticiaRepository);
        Mockito.verifyNoInteractions(editoriaRepository);
        Mockito.verifyNoInteractions(noticiaMapperOut);
        Mockito.verifyNoInteractions(editoriaMapperOut);
    }
}

