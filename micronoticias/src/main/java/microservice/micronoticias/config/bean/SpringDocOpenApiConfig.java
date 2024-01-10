package microservice.micronoticias.config.bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
            .components(new Components().addSecuritySchemes("security", securityScheme())) // Cria um componente responsável por linkar Swagger com Security
            .info(new Info()
                .title("Site - API Rest")
                .description("Site. API Rest escrita com Java 21 LTS e Spring Boot 3.0.12")
                .version("v1")
                .termsOfService("http://teste.com.br/terms-of-service") // URL fictícia
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0"))
                .contact(new Contact()
                    .name("Junior Martins")
                    .email("teste@email.com")) // Email fictício
            );
    }

    private SecurityScheme securityScheme() { // Método responsável por informar o Swagger sobre o Security. E da necessidade de Token nas requisições.

        return new SecurityScheme()
                .description("Insira um Bearer Token válido para prosseguir.")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("security"); // Essa string é a mesma usada lá nos recursos: security = {@SecurityRequirement(name = "security")}. Pode ser qualquer palavra.
    }
}

