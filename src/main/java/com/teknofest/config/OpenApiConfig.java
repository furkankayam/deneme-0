package com.teknofest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Furkan",
                        email = "furkan.36kaya@gmail.com"
                ),
                title = "Robot API",
                description = "Route-Following Robot",
                summary = "Robot that follows a line based on the entered route",
                version = "v1"
        ),
        servers = {
                @Server(
                        description = "LOCAL ENV",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}
