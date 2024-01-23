package microservice.micronoticias.adapter.in.controller;

import microservice.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import microservice.micronoticias.adapter.in.dto.request.NoticiaUpdateDtoIn;
import microservice.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import microservice.micronoticias.adapter.in.dto.response.NoticiaUpdateDtoOut;
import microservice.micronoticias.adapter.out.entity.EditoriaEntity;
import microservice.micronoticias.adapter.out.repository.EditoriaRepository;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/sql/editorias/editorias-insert.sql", "/sql/noticias/noticias-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration Controller - Notícia")
class NoticiaControllerIntegrationTest {

    private static final String END_POINT = "/api/v1/noticias";

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private EditoriaRepository editoriaRepository;

    // fixture ou Scaffolding
    private NoticiaCriarDtoIn.NoticiaCriarDtoInBuilder noticiaCriarDtoIn;

    private NoticiaUpdateDtoIn.NoticiaUpdateDtoInBuilder noticiaUpdateDtoIn;

    @BeforeEach
    void setUp() {
        noticiaCriarDtoIn = factory.gerarNoticiaCriarDtoInBuilder();
        noticiaUpdateDtoIn = factory.gerarNoticiaUpdateDtoInBuilder();
    }

    @AfterEach
    void tearDown() {
        noticiaRepository.deleteAll();
        editoriaRepository.deleteAll();
    }

    @Nested
    @DisplayName("Post")
    class PostNoticia {

        @Test
        @DisplayName("dados válidos com XML")
        void dadaNoticiaValida_QuandoCriarComContentNegotiationXML_EntaoRetornarHttp201() {

            var dtoIn = noticiaCriarDtoIn.build();

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
        void dadaNoticiaValida_QuandoCriarComContentNegotiationYAML_EntaoRetornarHttp201() {

            var dtoIn = noticiaCriarDtoIn.build();

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
        void dadoNoticiaValida_QuandoCriarComContentNegotiationJSon_EntaoRetornarNoticiaCriarDtoOutComDadosIguais() {

            var dtoIn = noticiaCriarDtoIn.build();

            webTestClient.post()
                .uri(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NoticiaCriarDtoOut.class)
                .consumeWith(response -> {
                    assertThat(response.getResponseBody().id()).isPositive();
                    assertThat(response.getResponseBody().chapeu()).isEqualTo(dtoIn.chapeu());
                    assertThat(response.getResponseBody().titulo()).isEqualTo(dtoIn.titulo());
                    assertThat(response.getResponseBody().linhaFina()).isEqualTo(dtoIn.linhaFina());
                    assertThat(response.getResponseBody().lide()).isEqualTo(dtoIn.lide());
                    assertThat(response.getResponseBody().corpo()).isEqualTo(dtoIn.corpo());
                    assertThat(response.getResponseBody().autorias().get(0)).isEqualTo(dtoIn.autorias().get(0));
                    assertThat(response.getResponseBody().fontes().get(0)).isEqualTo(dtoIn.fontes().get(0));
                    assertThat(response.getResponseBody().editorias()).hasSize(1);
                });
        }

        @Test
        @DisplayName("persistência")
        void dadaNoticiaValida_QuandoCriar_EntaoRetornarNoticiaCriarDtoOutComDadosPersistidos() {

            var dtoIn = noticiaCriarDtoIn.build();

            var resposta = webTestClient.post()
                .uri(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NoticiaCriarDtoOut.class)
                .returnResult().getResponseBody();

            var noticiaDoBanco = noticiaRepository.findById(resposta.id()).get();

            assertThat(noticiaDoBanco.getId()).isNotNull();
            assertThat(dtoIn.chapeu()).isEqualTo(noticiaDoBanco.getChapeu());
            assertThat(dtoIn.titulo()).isEqualTo(noticiaDoBanco.getTitulo());
            assertThat(dtoIn.linhaFina()).isEqualTo(noticiaDoBanco.getLinhaFina());
            assertThat(dtoIn.lide()).isEqualTo(noticiaDoBanco.getLide());
            assertThat(dtoIn.corpo()).isEqualTo(noticiaDoBanco.getCorpo());
            assertThat(dtoIn.autorias().get(0)).isEqualTo(noticiaDoBanco.getAutorias().get(0));
            assertThat(dtoIn.fontes().get(0)).isEqualTo(noticiaDoBanco.getFontes().get(0));
            assertThat(noticiaDoBanco.getEditorias()).hasSize(1);
        }

        @Test
        @DisplayName("duas novas editorias")
        void dadaNoticiaComDuasNovaEditorias_QuandoCriar_EntaoRetornarComDuasEditoriasSalvas() {
            var editoria1 = factory.gerarEditoriaDtoInBuilder().build();
            var editoria2 = factory.gerarEditoriaDtoInBuilder().build();

            var dtoIn = noticiaCriarDtoIn
                .editorias(Set.of(editoria1, editoria2))
                .build();

            var resposta = webTestClient.post()
                .uri(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NoticiaCriarDtoOut.class)
                .returnResult().getResponseBody();

            var noticiaDoBanco = noticiaRepository.findById(resposta.id()).get();

            assertThat(noticiaDoBanco.getEditorias()).hasSize(2);
            assertThat(noticiaDoBanco.getEditorias().stream().map(EditoriaEntity::getNomenclatura))
                .containsExactlyInAnyOrder(editoria1.nomenclatura(), editoria2.nomenclatura());
        }
    }

    @Nested
    @DisplayName("Delete")
    class DeleteNoticia {

        @Test
        @DisplayName("dados válidos com JSON")
        void dadoNoticiaValida_QuandoDeleteComContentNegotiationJson_EntaoRetornarComHttp204() {

            webTestClient.delete()
                .uri(END_POINT + "/" + 1001L)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent()
                .expectBody(Void.class);
        }
    }

    @Nested
    @DisplayName("Update")
    class UpdateNoticia {

        @Test
        @DisplayName("dados válidos com XML")
        void dadaNoticiaValida_QuandoUpdateComContentNegotiationXML_EntaoRetornarHttp200() {

            var dtoIn = noticiaUpdateDtoIn.id(1001L).build();

            webTestClient.put()
                .uri(END_POINT)
                .accept(MediaType.APPLICATION_XML)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_XML)
                .expectBody()
                    .consumeWith(response -> assertThat(response.getResponseBody()).isNotNull());
        }

        @Test
        @DisplayName("dados válidos com YAML")
        void dadaNoticiaValida_QuandoUpdateComContentNegotiationYAML_EntaoRetornarHttp200() {

            var dtoIn = noticiaUpdateDtoIn.id(1001L).build();

            webTestClient.put()
                .uri(END_POINT)
                .accept(MediaType.valueOf("application/x-yaml"))
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.valueOf("application/x-yaml"))
                .expectBody()
                    .consumeWith(response -> assertThat(response.getResponseBody()).isNotNull());
        }

        @Test
        @DisplayName("dados válidos com JSON")
        void dadoNoticiaValida_QuandoUpdateComContentNegotiationJSon_EntaoRetornarNoticiaUpdateDtoOutComDadosIguais() {

            var dtoIn = noticiaUpdateDtoIn.id(1001L).build();

            webTestClient.put()
                .uri(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NoticiaUpdateDtoOut.class)
                .consumeWith(response -> {
                    assertThat(response.getResponseBody().id()).isPositive();
                    assertThat(response.getResponseBody().chapeu()).isEqualTo(dtoIn.chapeu());
                    assertThat(response.getResponseBody().titulo()).isEqualTo(dtoIn.titulo());
                    assertThat(response.getResponseBody().linhaFina()).isEqualTo(dtoIn.linhaFina());
                    assertThat(response.getResponseBody().lide()).isEqualTo(dtoIn.lide());
                    assertThat(response.getResponseBody().corpo()).isEqualTo(dtoIn.corpo());
                    assertThat(response.getResponseBody().autorias().get(0)).isEqualTo(dtoIn.autorias().get(0));
                    assertThat(response.getResponseBody().fontes().get(0)).isEqualTo(dtoIn.fontes().get(0));
                    assertThat(response.getResponseBody().editorias()).hasSize(1);
                });
        }
    }
}

