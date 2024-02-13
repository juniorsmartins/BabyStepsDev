package microservice.microinscricoes.swagger;

import io.restassured.RestAssured;
import microservice.microinscricoes.utility.AbstractIntegrationTest;
import microservice.microinscricoes.utility.TestConfigs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    void shouldDisplaySwaggerUiPage() {

        var content = RestAssured.given()
            .basePath("/swagger-ui/index.html")
            .port(TestConfigs.SERVER_PORT)
            .when()
                .get()
            .then()
                .statusCode(200)
            .extract()
                .body()
                    .asString();

        Assertions.assertTrue(content.contains("Swagger UI"));
    }
}

