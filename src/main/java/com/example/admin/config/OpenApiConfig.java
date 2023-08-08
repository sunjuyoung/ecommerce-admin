package com.example.admin.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "admin",
                        url = "https://github.com/sunjuyoung/ecommerce-admin"
                ),
                title = "admin API",
                description = "admin API Documentation",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "dev Server",
                        url = "http://localhost:8080"
                ),
//                @Server(
//                        description = "Local Server",
//                        url = "http://localhost:8080"
//                )
        }
)
@SecurityScheme(
        name = "jwt-auth",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
