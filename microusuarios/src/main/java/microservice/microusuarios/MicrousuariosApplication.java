package microservice.microusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicrousuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrousuariosApplication.class, args);
	}
}

