package microservice.microinscricoes.adapter.in.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import microservice.microinscricoes.adapter.in.dto.TorneioIdDto;
import microservice.microinscricoes.adapter.in.dto.request.InscricaoFiltroDto;
import microservice.microinscricoes.adapter.in.dto.request.InscricaoOpenDtoIn;
import microservice.microinscricoes.adapter.in.dto.response.InscricaoOpenDtoOut;
import microservice.microinscricoes.adapter.out.repository.InscricaoRepository;
import microservice.microinscricoes.adapter.out.repository.TorneioRepository;
import microservice.microinscricoes.adapter.out.repository.entity.TorneioEntity;
import microservice.microinscricoes.utility.AbstractIntegrationTest;
import microservice.microinscricoes.utility.FactoryObjectMother;
import microservice.microinscricoes.utility.InscricaoOpenDtoPage;
import microservice.microinscricoes.utility.TestConfigs;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    private TorneioEntity torneioEntity1;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // Usar somente nos testes para manter a segurança da API - Isso é usado quanto temos Hateoas

        var torneioIdDto = new TorneioIdDto(1L);
        this.inscricaoOpenDtoIn = this.factory.gerarInscricaoOpenDtoInBuilder()
            .torneio(torneioIdDto)
            .build();

        torneioEntity1 = this.factory.gerarTorneioEntityBuilder()
            .id(1L)
            .build();
        this.torneioRepository.save(torneioEntity1);

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
        @DisplayName("persistência")
        void dadoInscricaoOpenDtoInValido_QuandoOpen_EntaoRetornarDadosPersistidos() throws IOException {

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
    @DisplayName("Pesquisar")
    class Pesquisar {

        @Test
        @DisplayName("http 200")
        void dadoGetValido_QuandoPesquisar_EntaoRetornarHttp200() throws IOException {

            var inscricaoEntity1 = factory.gerarInscricaoEntityBuilder()
                .torneio(torneioEntity1)
                .build();
            inscricaoRepository.save(inscricaoEntity1);

            var torneioEntity2 = factory.gerarTorneioEntityBuilder()
                .id(2L)
                .build();
            torneioRepository.save(torneioEntity2);

            var inscricaoEntity2 = factory.gerarInscricaoEntityBuilder()
                .torneio(torneioEntity2)
                .build();
            inscricaoRepository.save(inscricaoEntity2);

            var filtro = new InscricaoFiltroDto();
            filtro.setId("2");

            RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .queryParam("filtro.id", "2")
                .when()
                    .get()
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("content.size()", Matchers.equalTo(1));
        }
    }
}

