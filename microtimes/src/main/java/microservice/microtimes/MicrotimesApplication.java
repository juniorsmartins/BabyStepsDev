package microservice.microtimes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
@EnableDiscoveryClient
public class MicrotimesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrotimesApplication.class, args);
	}

}
