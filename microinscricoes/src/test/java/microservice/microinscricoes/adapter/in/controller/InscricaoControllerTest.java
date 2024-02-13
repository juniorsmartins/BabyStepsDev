package microservice.microinscricoes.adapter.in.controller;

import io.restassured.RestAssured;
import microservice.microinscricoes.adapter.in.dto.TorneioIdDto;
import microservice.microinscricoes.adapter.in.dto.request.InscricaoOpenDtoIn;
import microservice.microinscricoes.adapter.out.repository.InscricaoRepository;
import microservice.microinscricoes.adapter.out.repository.TorneioRepository;
import microservice.microinscricoes.utility.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@DisplayName("Integration - InscricaoController")
class InscricaoControllerTest {

    private static final int SERVER_PORT = 8000;

    public static final String APPLICATION_JSON = "application/json";

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private TorneioRepository torneioRepository;

    private InscricaoOpenDtoIn inscricaoOpenDtoIn;

    @BeforeEach
    void setUp() {
        var torneioIdDto = new TorneioIdDto(1L);
        this.inscricaoOpenDtoIn = this.factory.gerarInscricaoOpenDtoInBuilder()
            .torneio(torneioIdDto)
            .build();

        var torneioEntity = this.factory.gerarTorneioEntityBuilder()
            .id(1L)
            .build();
        this.torneioRepository.save(torneioEntity);
    }

    @AfterEach
    void tearDown() {
        this.inscricaoRepository.deleteAll();
        this.torneioRepository.deleteAll();
    }

    @Nested
    @DisplayName("Post")
    class Post {

        @Test
        @DisplayName("dados válidos")
        void dadoInscricaoOpenDtoInValido_QuandoOpen_EntaoRetornarHttp201() {

            RestAssured.given()
                    .contentType(APPLICATION_JSON)
                    .body(inscricaoOpenDtoIn)
                .when()
                    .post("http://localhost:8000/api/v1/inscricoes")
                .then()
                    .log().all()
                    .statusCode(201);
        }
    }
}

