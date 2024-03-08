package microservice.microtimes.adapter.in.controller;

import microservice.microtimes.adapter.in.controller.dto.request.TimeCreateDtoRequest;
import microservice.microtimes.adapter.out.repository.TimeRepository;
import microservice.microtimes.utility.AbstractTestcontainersTest;
import microservice.microtimes.utility.ConverterUtilTest;
import microservice.microtimes.utility.FactoryObjectMother;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Integration - TimeController")
class TimeControllerTest extends AbstractTestcontainersTest {

    public static final String BASE_PATH = "/api/v1/times";

    public static final String UTF_8 = "UTF-8";

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TimeRepository timeRepository;

    private TimeCreateDtoRequest.TimeCreateDtoRequestBuilder timeCreateDtoRequestBuilder;

    @BeforeEach
    void setUp() {
        timeCreateDtoRequestBuilder = factory.gerarTimeCreateDtoRequestBuilder();
    }

    @AfterEach
    void tearDown() {
        this.timeRepository.deleteAll();
    }

    @Nested
    @DisplayName("Post")
    class Post {

        @Test
        @Order(1)
        @DisplayName("http 201")
        void dadoDadosValidos_quandoPostTime_entaoRetornarHttp201() throws Exception {

            var timeDto = timeCreateDtoRequestBuilder.build();

            mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(UTF_8)
                    .content(ConverterUtilTest.converterObjetoParaJson(timeDto))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        @Order(2)
        @DisplayName("persistência")
        void dadoTimeValido_quandoPostTime_entaoRetornarValoresIguaisAndPersistidos() throws Exception {

            var timeRequest = timeCreateDtoRequestBuilder.build();

            var responseBody = mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(UTF_8)
                    .content(ConverterUtilTest.converterObjetoParaJson(timeRequest))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpectAll(
                    MockMvcResultMatchers.jsonPath("$.nomeFantasia", Matchers.equalToIgnoringCase(timeRequest.nomeFantasia())),
                    MockMvcResultMatchers.jsonPath("$.razaoSocial", Matchers.equalToIgnoringCase(timeRequest.razaoSocial())),
                    MockMvcResultMatchers.jsonPath("$.cnpj", Matchers.equalTo(timeRequest.cnpj())),
                    MockMvcResultMatchers.jsonPath("$.estado", Matchers.equalToIgnoringCase(timeRequest.estado())),
                    MockMvcResultMatchers.jsonPath("$.cidade", Matchers.equalToIgnoringCase(timeRequest.cidade())),
                    MockMvcResultMatchers.jsonPath("$.dataFundacao", Matchers.equalTo(timeRequest.dataFundacao().toString())),
                    MockMvcResultMatchers.jsonPath("$.descricao", Matchers.equalToIgnoringCase(timeRequest.descricao())),
                    MockMvcResultMatchers.jsonPath("$.nomePresidente", Matchers.equalToIgnoringCase(timeRequest.nomePresidente())),
                    MockMvcResultMatchers.jsonPath("$.nomeVicePresidente", Matchers.equalToIgnoringCase(timeRequest.nomeVicePresidente())),
                    MockMvcResultMatchers.jsonPath("$.nomeHeadCoach", Matchers.equalToIgnoringCase(timeRequest.nomeHeadCoach())))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

            var timeResponse = ConverterUtilTest.converterJsonParaTimeCreateDtoResponse(responseBody);

            Assertions.assertTrue(timeResponse.id() > 0);
            Assertions.assertEquals(timeRequest.nomeFantasia(), timeResponse.nomeFantasia());
            Assertions.assertEquals(timeRequest.razaoSocial(), timeResponse.razaoSocial());
            Assertions.assertEquals(timeRequest.cnpj(), timeResponse.cnpj());
            Assertions.assertEquals(timeRequest.estado(), timeResponse.estado());
            Assertions.assertEquals(timeRequest.cidade(), timeResponse.cidade());
            Assertions.assertEquals(timeRequest.dataFundacao(), timeResponse.data());
            Assertions.assertEquals(timeRequest.descricao(), timeResponse.descricao());
            Assertions.assertEquals(timeRequest.nomePresidente(), timeResponse.presidente());
            Assertions.assertEquals(timeRequest.nomeVicePresidente(), timeResponse.vicePresidente());
            Assertions.assertEquals(timeRequest.nomeHeadCoach(), timeResponse.headCoach());
        }
    }


}

