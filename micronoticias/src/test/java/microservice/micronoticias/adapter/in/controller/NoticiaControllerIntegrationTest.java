package microservice.micronoticias.adapter.in.controller;

import microservice.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import microservice.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import microservice.micronoticias.adapter.out.repository.NoticiaRepository;
import microservice.micronoticias.utility.FactoryObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Integration Controller - Notícia")
class NoticiaControllerIntegrationTest {

    private static final String END_POINT = "/api/v1/noticias";

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private NoticiaRepository noticiaRepository;

    private NoticiaCriarDtoIn.NoticiaCadastrarDtoInBuilder noticiaCadastrarDtoIn;

    @BeforeEach
    void setUp() {
        noticiaCadastrarDtoIn = factory.gerarNoticiaCadastrarDtoInBuilder();
    }

    @Nested
    @DisplayName("Post")
    class PostNoticia {

        @Test
        @DisplayName("dados validos com XML")
        void dadaNoticiaValida_QuandoCriarComContentNegotiationXML_EntaoRetornarHttp201() {

            var dtoIn = noticiaCadastrarDtoIn.build();

            webTestClient.post()
                .uri(END_POINT)
                .accept(MediaType.APPLICATION_XML)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_XML)
                .expectBody().consumeWith(response -> {
                    assertThat(response.getResponseBody()).isNotNull();
                });
        }

        @Test
        @DisplayName("dados válidos com JSON")
        void dadoNoticiaValida_QuandoCriarComContentNegotiationJSon_EntaoRetornarNoticiaCriarDtoOutComDadosIguaisEntradaAndHttp201() {

            var dtoIn = noticiaCadastrarDtoIn.build();

            webTestClient.post()
                .uri(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NoticiaCriarDtoOut.class)
                .consumeWith(response -> {
                    assertThat(response.getResponseBody()).isNotNull();
                    assertThat(response.getResponseBody().chapeu()).isEqualTo(dtoIn.chapeu());
                    assertThat(response.getResponseBody().titulo()).isEqualTo(dtoIn.titulo());
                    assertThat(response.getResponseBody().linhaFina()).isEqualTo(dtoIn.linhaFina());
                    assertThat(response.getResponseBody().lide()).isEqualTo(dtoIn.lide());
                    assertThat(response.getResponseBody().corpo()).isEqualTo(dtoIn.corpo());
                    assertThat(response.getResponseBody().autorias().get(0)).isEqualTo(dtoIn.autorias().get(0));
                    assertThat(response.getResponseBody().fontes().get(0)).isEqualTo(dtoIn.fontes().get(0));
                });
        }

        @Test
        @DisplayName("persistência")
        void dadaNoticiaValida_QuandoCriar_EntaoRetornarNoticiaCriarDtoOutComDadosPersistidos() {

            var dtoIn = noticiaCadastrarDtoIn.build();

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
        }
    }
}

