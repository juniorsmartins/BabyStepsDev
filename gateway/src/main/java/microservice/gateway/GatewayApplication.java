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

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {

		return routeLocatorBuilder
			.routes()
				.route(rota -> rota.path("/api/v1/noticias/**").uri("lb://micronoticias")) // lb = Load Balance
				.route(rota -> rota.path("/api/v1/editorias/**").uri("lb://micronoticias"))
				.route(rota -> rota.path("/api/v1/persons/**").uri("lb://microusuarios"))
				.route(rota -> rota.path("/api/v1/inscricoes/**").uri("lb://microinscricoes"))
				.route(rota -> rota.path("/api/v1/torneios/**").uri("lb://microinscricoes"))
				.route(rota -> rota.path("/api/v1/times/**").uri("lb://microtimes"))
			.build();
	}
}

