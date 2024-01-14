package microservice.micronoticias.adapter.out.mapper;

import microservice.micronoticias.config.exception.http_500.EditoriaMapperOutImplException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit Mapper - Converter Editoria")
class EditoriaMapperOutImplUnitTest {

    @Autowired
    private EditoriaMapperOutImpl editoriaMapperOut;

    @Test
    @DisplayName("nulo ToEditoriaEntity")
    void dadoEditoriaNula_QuandoConverterToEditoriaEntity_EntaoLancarException() {
        Executable acao = () -> this.editoriaMapperOut.toEditoriaEntity(null);
        Assertions.assertThrows(EditoriaMapperOutImplException.class, acao);
    }

    @Test
    @DisplayName("nulo ToEditoria")
    void dadoEditoriaNula_QuandoConverterToEditoria_EntaoLancarException() {
        Executable acao = () -> this.editoriaMapperOut.toEditoria(null);
        Assertions.assertThrows(EditoriaMapperOutImplException.class, acao);
    }
}

