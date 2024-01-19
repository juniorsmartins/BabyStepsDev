package microservice.micronoticias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicronoticiasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicronoticiasApplication.class, args);
	}
}

