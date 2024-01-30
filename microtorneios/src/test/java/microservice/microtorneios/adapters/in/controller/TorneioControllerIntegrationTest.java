package microservice.microtorneios.adapters.in.controller;

import microservice.microtorneios.adapters.in.dto.request.TimeInventoryDtoId;
import microservice.microtorneios.adapters.in.dto.request.TorneioCreateDtoRequest;
import microservice.microtorneios.adapters.in.dto.response.TorneioCreateDtoResponse;
import microservice.microtorneios.adapters.out.repository.TimeInventoryRepository;
import microservice.microtorneios.adapters.out.repository.TorneioRepository;
import microservice.microtorneios.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/sql/times/times-insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integration Controller - Torneios")
class TorneioControllerIntegrationTest {

    private static final String END_POINT = "/api/v1/torneios";

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TorneioRepository torneioRepository;

    @Autowired
    private TimeInventoryRepository timeInventoryRepository;

    private TorneioCreateDtoRequest.TorneioCreateDtoRequestBuilder torneioCreateDtoRequestBuilder;

    @BeforeEach
    void setUp() {
        torneioCreateDtoRequestBuilder = factory.gerarTorneioCreateDtoRequest();
    }

    @AfterEach
    void tearDown() {
        this.torneioRepository.deleteAll();
        this.timeInventoryRepository.deleteAll();
    }

    @Nested
    @DisplayName("Criar")
    class Create {

        @Test
        @DisplayName("dados válidos com JSON")
        void dadoEditoriaValida_QuandoCriarComContentNegotiationJSon_EntaoRetornarDadosIguaisSalvos() {

            var idTime1 = new TimeInventoryDtoId(1001L);
            var idTime2 = new TimeInventoryDtoId(1002L);

            var dtoIn = torneioCreateDtoRequestBuilder
                .nome("Campeonato Brasileiro de Futebol Americano")
                .ano(2024)
                .times(Set.of(idTime1, idTime2))
                .build();

            webTestClient.post()
                .uri(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TorneioCreateDtoResponse.class)
                .consumeWith(response -> {
                    assertThat(response.getResponseBody().id()).isPositive();
                    assertThat(response.getResponseBody().times().size()).isEqualTo(2);
                });
        }
    }
}

