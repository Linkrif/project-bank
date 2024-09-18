package br.com.projectbank.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfiguration{
    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Bank-api")
                    .termsOfService("http://swagger.io/terms/")
                    .license(
                        License().name("Apache 2.0")
                            .url("http://springdoc.org")
                    )
            )
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearerToken",
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .description("Bearer Token")
                            .`in`(SecurityScheme.In.HEADER)
                            .bearerFormat("JWT")
                            .scheme("bearer")
                            .name("Authorization")
                    )
            )
            .security(listOf(SecurityRequirement().addList("bearerToken")))
    }
}