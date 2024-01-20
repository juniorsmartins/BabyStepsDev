package microservice.micronoticias.adapter.in.controller;

import microservice.micronoticias.adapter.in.dto.request.EditoriaCriarDtoIn;
import microservice.micronoticias.adapter.in.dto.response.EditoriaCriarDtoOut;
import microservice.micronoticias.adapter.in.dto.response.EditoriaListarDtoOut;
import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/sql/editorias/editorias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration Controller - Editoria")
class EditoriaControllerIntegrationTest {

    private static final String END_POINT = "/api/v1/editorias";

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EditoriaRepository editoriaRepository;

    // Fixture ou Scaffolding
    private EditoriaCriarDtoIn.EditoriaCriarDtoInBuilder editoriaCriarDtoInBuilder;

    private EditoriaEntity editoriaEntity;

    @BeforeEach
    void setUp() {
        editoriaCriarDtoInBuilder = factory.gerarEditoriaCriarDtoInBuilder();
        editoriaEntity = this.editoriaRepository.findById(1001L).get();
    }

    @AfterEach
    void tearDown() {
        this.editoriaRepository.deleteAll();
    }

    @Nested
    @DisplayName("Criar")
    class Criar {

        @Test
        @DisplayName("dados válidos com XML")
        void dadoEditoriaValida_QuandoCriarComContentNegotiationXML_EntaoRetornarHttp201() {

            var dtoIn = editoriaCriarDtoInBuilder.build();

            webTestClient.post()
                .uri(END_POINT)
                .accept(MediaType.APPLICATION_XML)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_XML)
                .expectBody().consumeWith(response -> assertThat(response.getResponseBody()).isNotNull());
        }

        @Test
        @DisplayName("dados válidos com YAML")
        void dadoEditoriaValida_QuandoCriarComContentNegotiationYAML_EntaoRetornarHttp201() {

            var dtoIn = editoriaCriarDtoInBuilder.build();

            webTestClient.post()
                .uri(END_POINT)
                .accept(MediaType.valueOf("application/x-yaml"))
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.valueOf("application/x-yaml"))
                .expectBody().consumeWith(response -> assertThat(response.getResponseBody()).isNotNull());
        }

        @Test
        @DisplayName("dados válidos com JSON")
        void dadoEditoriaValida_QuandoCriarComContentNegotiationJSon_EntaoRetornarDadosIguaisSalvos() {

            var dtoIn = editoriaCriarDtoInBuilder.build();

            webTestClient.post()
                .uri(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EditoriaCriarDtoOut.class)
                .consumeWith(response -> {
                    assertThat(response.getResponseBody().id()).isPositive();
                    assertThat(response.getResponseBody().nomenclatura()).isEqualTo(dtoIn.nomenclatura());
                    assertThat(response.getResponseBody().descricao()).isEqualTo(dtoIn.descricao());
                });
        }
    }

    @Nested
    @DisplayName("Listar")
    class Listar {

        @Test
        @DisplayName("dois itens XML")
        void dadoDuasEditorias_QuandoListarComContentNegotiationXML_EntaoRetornarListaComDoisItens() {

            webTestClient.get()
                .uri(END_POINT)
                .accept(MediaType.APPLICATION_XML)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EditoriaListarDtoOut.class)
                .consumeWith(response -> {
                    assertThat(response.getResponseBody().size()).isEqualTo(2);
                });
        }

        @Test
        @DisplayName("dois itens YAML")
        void dadoDuasEditorias_QuandoListarComContentNegotiationYAML_EntaoRetornarListaComDoisItens() {

            webTestClient.get()
                .uri(END_POINT)
                .accept(MediaType.valueOf("application/x-yaml"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EditoriaListarDtoOut.class)
                .consumeWith(response -> {
                    assertThat(response.getResponseBody().size()).isEqualTo(2);
                });
        }

        @Test
        @DisplayName("dois itens")
        void dadoDuasEditorias_QuandoListar_EntaoRetornarListaComDoisItens() {

            webTestClient.get()
                .uri(END_POINT)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EditoriaListarDtoOut.class)
                .consumeWith(response -> {
                    assertThat(response.getResponseBody().size()).isEqualTo(2);
                });
        }
    }

    @Nested
    @DisplayName("DeletarPorId")
    class DeletarPorId {

        @Test
        @DisplayName("id válido XML")
        void dadoIdValido_QuandoDeletarPorIdComContentNegotiationXML_EntaoRetornarHttpNoContent() {

            webTestClient.delete()
                .uri(END_POINT + "/" + editoriaEntity.getId())
                .accept(MediaType.APPLICATION_XML)
                .exchange()
                .expectStatus().isNoContent()
                .expectBody();
        }

        @Test
        @DisplayName("id válido YAML")
        void dadoIdValido_QuandoDeletarPorIdComContentNegotiationYAML_EntaoRetornarHttpNoContent() {

            webTestClient.delete()
                .uri(END_POINT + "/" + editoriaEntity.getId())
                .accept(MediaType.valueOf("application/x-yaml"))
                .exchange()
                .expectStatus().isNoContent()
                .expectBody();
        }

        @Test
        @DisplayName("id válido")
        void dadoIdValido_QuandoDeletarPorId_EntaoRetornarHttpNoContent() {

            webTestClient.delete()
                .uri(END_POINT + "/" + editoriaEntity.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent()
                .expectBody();
        }
    }
}

