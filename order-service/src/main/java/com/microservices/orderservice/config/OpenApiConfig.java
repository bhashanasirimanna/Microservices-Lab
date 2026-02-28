package com.microservices.orderservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI orderServiceOpenAPI() {
                return new OpenAPI()
                                .addServersItem(new io.swagger.v3.oas.models.servers.Server()
                                                .url("http://localhost:8080")
                                                .description("API Gateway"))
                                .info(new Info()
                                                .title("Order Service API")
                                                .description("REST API for managing orders in the microservices system")
                                                .version("v1.0.0")
                                                .contact(new Contact()
                                                                .name("Microservices Team")
                                                                .email("team@microservices.com"))
                                                .license(new License()
                                                                .name("Apache 2.0")
                                                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
        }
}
