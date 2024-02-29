package microservice.microinscricoes.configs;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import microservice.microinscricoes.adapter.in.controller.dto.TorneioIdDto;
import microservice.microinscricoes.adapter.in.controller.dto.request.InscricaoCreateDtoIn;
import microservice.microinscricoes.adapter.out.repository.InscricaoRepository;
import microservice.microinscricoes.adapter.out.repository.TorneioRepository;
import microservice.microinscricoes.utility.AbstractIntegrationTest;
import microservice.microinscricoes.utility.FactoryObjectMother;
import microservice.microinscricoes.utility.TestConfigs;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Integration - InscricaoController")
class CorsIntegrationTest extends AbstractIntegrationTest {

    public static final String BASE_PATH = "/api/v1/inscricoes";

    private static RequestSpecification requestSpecification;

    private static ObjectMapper objectMapper;

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private TorneioRepository torneioRepository;

    private InscricaoCreateDtoIn inscricaoOpenDtoIn;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // Usar somente nos testes para manter a segurança da API - Isso é usado quanto temos Hateoas

        var torneioIdDto = new TorneioIdDto(1L);
        this.inscricaoOpenDtoIn = this.factory.gerarInscricaoCreateDtoInBuilder()
            .torneio(torneioIdDto)
            .build();

        var torneioEntity = this.factory.gerarTorneioEntityBuilder()
            .id(1L)
            .build();
        this.torneioRepository.save(torneioEntity);

        requestSpecification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_BABYSTEPS)
            .setBasePath(BASE_PATH)
            .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();
    }

    @AfterEach
    void tearDown() {
        this.inscricaoRepository.deleteAll();
        this.torneioRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("http 201")
    void dadoInscricaoOpenDtoInValido_QuandoOpen_EntaoRetornarHttp201() {

        RestAssured
            .given().spec(requestSpecification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(inscricaoOpenDtoIn)
            .when()
                .post()
            .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    @Order(2)
    @DisplayName("http 403")
    void dadoTesteDeCors_QuandoCorsNaoPermitido_EntaoRetornarHttp403() {

        requestSpecification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_NAO_PERMITIDA)
            .setBasePath(BASE_PATH)
            .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        var response = RestAssured
            .given().spec(requestSpecification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(inscricaoOpenDtoIn)
            .when()
                .post()
            .then()
                .log().all()
                .statusCode(403)
                    .extract()
                        .body()
                            .asString();

        Assertions.assertEquals("Invalid CORS request", response);
    }
}

