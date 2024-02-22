package microservice.microtimes.adapter.in.controller;

import microservice.microtimes.utility.AbstractTestcontainersTest;
import microservice.microtimes.utility.ConverterUtilTest;
import microservice.microtimes.utility.FactoryObjectMother;
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
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @Order(1)
    @DisplayName("http 201")
    void dadoDadosValidos_quandoPostTime_entaoRetornarHttp201() throws Exception {

        var time = this.factory.gerarTimeCreateDtoRequestBuilder().build();

        this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(UTF_8)
                .content(ConverterUtilTest.converterObjetoParaJson(time))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andDo(MockMvcResultHandlers.print());
    }
}

