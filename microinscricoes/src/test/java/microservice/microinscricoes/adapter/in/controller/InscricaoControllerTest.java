package microservice.microinscricoes.adapter.in.controller;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import microservice.microinscricoes.adapter.in.dto.TorneioIdDto;
import microservice.microinscricoes.adapter.in.dto.request.InscricaoOpenDtoIn;
import microservice.microinscricoes.adapter.in.dto.response.InscricaoOpenDtoOut;
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

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Integration - InscricaoController")
class InscricaoControllerTest extends AbstractIntegrationTest {

    public static final String BASE_PATH = "/api/v1/inscricoes";

    private static RequestSpecification requestSpecification;

    private static ObjectMapper objectMapper;

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private TorneioRepository torneioRepository;

    private InscricaoOpenDtoIn inscricaoOpenDtoIn;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // Usar somente nos testes para manter a segurança da API - Isso é usado quanto temos Hateoas

        var torneioIdDto = new TorneioIdDto(1L);
        this.inscricaoOpenDtoIn = this.factory.gerarInscricaoOpenDtoInBuilder()
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

    @Nested
    @DisplayName("Post")
    class Post {

        @Test
        @DisplayName("http 201")
        void dadoInscricaoOpenDtoInValido_QuandoOpen_EntaoRetornarHttp201() {

            RestAssured.given().spec(requestSpecification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(inscricaoOpenDtoIn)
                    .when()
                    .post()
                .then()
                    .log().all()
                    .statusCode(201);
        }

        @Test
        @DisplayName("persistência")
        void dadoInscricaoOpenDtoInValido_QuandoOpen_EntaoRetornarDadosPersistidos() throws IOException {

            var response = RestAssured.given().spec(requestSpecification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(inscricaoOpenDtoIn)
                    .when()
                    .post()
                .then()
                    .log().all()
                    .statusCode(201)
                .extract()
                    .body()
                        .asString();

            var dtoOut = objectMapper.readValue(response, InscricaoOpenDtoOut.class);
            var persistido = inscricaoRepository.findById(dtoOut.getId()).orElseThrow();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Assertions.assertEquals(dtoOut.getDataInicio(), persistido.getDataInicio().format(formatter));
            Assertions.assertEquals(dtoOut.getDataFim(), persistido.getDataFim().format(formatter));
            Assertions.assertEquals(dtoOut.getStatus(), persistido.getStatus());
            Assertions.assertEquals(dtoOut.getTorneio().getId(), persistido.getTorneio().getId());
        }
    }
}

