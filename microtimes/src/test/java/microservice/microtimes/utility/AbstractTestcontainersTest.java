package microservice.microtimes.utility;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractTestcontainersTest.Initializer.class)
public abstract class AbstractTestcontainersTest {

    // Test Containers
    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static MariaDBContainer<?> mariadb = new MariaDBContainer<>("mariadb:10.6.16-focal");

        private static void startContainers() {
            Startables.deepStart(Stream.of(mariadb)).join();
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testContainers = new MapPropertySource("testContainers", (Map) createConnectionConfiguration());
            environment.getPropertySources().addFirst(testContainers);
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of("spring.datasource.url", mariadb.getJdbcUrl(),
                    "spring.datasource.username", mariadb.getUsername(),
                    "spring.datasource.password", mariadb.getPassword());
        }
    }
}

