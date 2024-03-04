package microservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	private static final String MICROINSCRICOES = "lb://microinscricoes";

	private static final String MICRONOTICIAS = "lb://micronoticias";

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {

		return routeLocatorBuilder
			.routes()
				.route(rota -> rota.path("/api/v1/noticias/**").uri(MICRONOTICIAS)) // lb = Load Balance
				.route(rota -> rota.path("/api/v1/editorias/**").uri(MICRONOTICIAS))
				.route(rota -> rota.path("/api/v1/persons/**").uri("lb://microusuarios"))
				.route(rota -> rota.path("/api/v1/inscricoes/**").uri(MICROINSCRICOES))
				.route(rota -> rota.path("/api/v1/inscritos/**").uri(MICROINSCRICOES))
				.route(rota -> rota.path("/api/v1/eventos/**").uri(MICROINSCRICOES))
				.route(rota -> rota.path("/api/v1/torneios/**").uri("lb://microtorneios"))
				.route(rota -> rota.path("/api/v1/times/**").uri("lb://microtimes"))
			.build();
	}
}

