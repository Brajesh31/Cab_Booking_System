// package com.cabbookingsystem.config;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
// import io.swagger.v3.oas.annotations.info.Contact;
// import io.swagger.v3.oas.annotations.info.Info;
// import io.swagger.v3.oas.annotations.info.License;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;
// import io.swagger.v3.oas.annotations.servers.Server;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// @OpenAPIDefinition(
//     info = @Info(
//         title = "Cab Booking System API",
//         description = "This API provides all the endpoints for users, drivers, and admins in the cab booking system.",
//         version = "1.0",
//         contact = @Contact(name = "Srujan", email = "srujan@technifyz.in"),
//         license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
//     ),
//     servers = {
//         @Server(url = "http://localhost:8080", description = "Local Server")
//     },
//     security = {
//         @SecurityRequirement(name = "bearerAuth")
//     }
// )
// @SecurityScheme(
//     name = "bearerAuth",
//     type = SecuritySchemeType.HTTP,
//     scheme = "bearer",
//     bearerFormat = "JWT"
// )
// public class OpenAPIConfig {
// }
