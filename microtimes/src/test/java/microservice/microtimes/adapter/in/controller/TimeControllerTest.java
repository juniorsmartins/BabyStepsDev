package microservice.microtimes.adapter.in.controller;

import microservice.microtimes.utility.AbstractTestcontainersTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Integration - TimeController")
class TimeControllerTest extends AbstractTestcontainersTest {

    public static final String BASE_PATH = "/api/v1/times";

    public static final String UTF_8 = "UTF-8";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }
}

