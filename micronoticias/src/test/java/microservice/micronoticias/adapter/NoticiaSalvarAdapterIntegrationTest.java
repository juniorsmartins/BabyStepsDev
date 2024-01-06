package microservice.micronoticias.adapter;

import microservice.micronoticias.adapter.out.entity.NoticiaEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Sql(scripts = "/sql/noticias/noticias-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = "/sql/noticias/noticias-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DisplayName("Notícia Salvar Adapter - Integração")
class NoticiaSalvarAdapterIntegrationTest {

    @Autowired
    private NoticiaSalvarAdapter noticiaSalvarAdapter;

    @Test
    void dadoNoticiaValida_QuandoSalvar_EntaoRetornarComId() {

        var noticiaEntity = NoticiaEntity.builder()
                .build();

        var noticiaSalva = this.noticiaSalvarAdapter.salvar(noticiaEntity);
        Assertions.assertNotNull(noticiaSalva.getId());
    }
}

