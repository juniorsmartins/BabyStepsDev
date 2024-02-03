package microservice.microinscricoes.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.util.TimeZone;

public abstract class BaseTest {

    public static FactoryObjectMother factory;

    @BeforeAll
    public static void setUp() {
        factory = FactoryObjectMother.singleton();

        RestAssured.baseURI = "http://localhost:8000";
        RestAssured.basePath = "";
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}

