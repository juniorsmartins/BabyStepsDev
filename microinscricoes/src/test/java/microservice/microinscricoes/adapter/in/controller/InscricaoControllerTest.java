package microservice.microinscricoes.adapter.in.controller;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import microservice.microinscricoes.adapter.in.dto.TorneioIdDto;
import microservice.microinscricoes.adapter.in.dto.response.InscricaoOpenDtoOut;
import microservice.microinscricoes.adapter.out.repository.InscricaoRepository;
import microservice.microinscricoes.adapter.out.repository.TorneioRepository;
import microservice.microinscricoes.adapter.out.repository.entity.InscricaoEntity;
import microservice.microinscricoes.adapter.out.repository.entity.TorneioEntity;
import microservice.microinscricoes.utility.AbstractIntegrationTest;
import microservice.microinscricoes.utility.FactoryObjectMother;
import microservice.microinscricoes.utility.TestConfigs;
import org.hamcrest.Matchers;
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

    private InscricaoEntity inscricaoSalva;

    private TorneioEntity torneioSalvo2;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // Usar somente nos testes para manter a segurança da API - Isso é usado quanto temos Hateoas

        var torneioEntity1 = this.factory.gerarTorneioEntityBuilder()
            .id(1L)
            .build();
        this.torneioRepository.save(torneioEntity1);

        var inscricaoEntity1 = factory.gerarInscricaoEntityBuilder()
                .torneio(torneioEntity1)
                .build();
        inscricaoSalva = inscricaoRepository.save(inscricaoEntity1);

        var torneioEntity2 = factory.gerarTorneioEntityBuilder()
                .id(2L)
                .build();
        torneioSalvo2 = torneioRepository.save(torneioEntity2);

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
    @DisplayName("PostOpen")
    class PostOpen {

        @Test
        @DisplayName("persistência")
        void dadoInscricaoOpenDtoInValido_QuandoOpen_EntaoRetornarDadosPersistidos() throws IOException {

            var torneioIdDto = new TorneioIdDto(1L);
            var inscricaoOpenDtoIn = factory.gerarInscricaoOpenDtoInBuilder()
                .torneio(torneioIdDto)
                .build();

            var response = RestAssured
                .given().spec(requestSpecification)
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

    @Nested
    @DisplayName("GetPesquisar")
    class GetPesquisar {

        @Test
        @DisplayName("http 200")
        void dadoGetValido_QuandoPesquisar_EntaoRetornarHttp200() throws IOException {

            var inscricaoEntity2 = factory.gerarInscricaoEntityBuilder()
                .torneio(torneioSalvo2)
                .build();
            inscricaoRepository.save(inscricaoEntity2);

            RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when()
                    .get()
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("totalElements", Matchers.equalTo(2));
        }

        @Test
        @DisplayName("filtro por Id")
        void dadoGetValido_QuandoPesquisarPorId_EntaoRetornarUmItem() throws IOException {

            var inscricaoEntity2 = factory.gerarInscricaoEntityBuilder()
                .torneio(torneioSalvo2)
                .build();
            inscricaoRepository.save(inscricaoEntity2);

            RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .queryParam("id", inscricaoSalva.getId())
                .when()
                    .get()
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("totalElements", Matchers.equalTo(1));
        }
    }
}

