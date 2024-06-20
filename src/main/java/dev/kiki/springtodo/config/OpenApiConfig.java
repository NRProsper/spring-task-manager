package dev.kiki.springtodo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact (
                        name = "Nishimwe Prosper",
                        email = "prosper.rk1@gmail.com",
                        url = "dev.kiki.com"
                ),
                description = "OpenAPI documentation of a Task Manager API v.1.0",
                title = "OpenAPI specification - Kiki",
                version = "v.1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080/api/v1/tasks"
                )
        }
)
public class OpenApiConfig {
}
