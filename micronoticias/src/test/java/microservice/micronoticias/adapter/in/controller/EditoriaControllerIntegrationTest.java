package microservice.micronoticias.adapter.in.controller;

import microservice.micronoticias.adapter.in.dto.request.EditoriaCriarDtoIn;
import microservice.micronoticias.adapter.in.dto.response.EditoriaCriarDtoOut;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    @BeforeEach
    void setUp() {
        editoriaCriarDtoInBuilder = factory.gerarEditoriaCriarDtoInBuilder();
    }

    @AfterEach
    void tearDown() {
        this.editoriaRepository.deleteAll();
    }

    @Nested
    @DisplayName("Post")
    class PostEditoria {

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
}

