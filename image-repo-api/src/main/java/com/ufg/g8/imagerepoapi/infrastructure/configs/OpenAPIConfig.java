package com.ufg.g8.imagerepoapi.infrastructure.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
            title = "Pixel Port API",
            description = "Platform for those who want to share there work",
            version = "3.0.0"
    )
)
public class OpenAPIConfig {

    @Bean
    public GroupedOpenApi authenticatedControllers() {
        return GroupedOpenApi.builder()
                .group("Authenticated Endpoints")
                .packagesToScan("com.ufg.g8.imagerepoapi.presentation.controllers")
                .build();
    }

    @Bean
    public GroupedOpenApi publicControllers() {
        return GroupedOpenApi.builder()
                .group("Public Endpoints")
                .packagesToScan("com.ufg.g8.imagerepoapi.presentation.controllers.publics")
                .build();
    }

    @Bean
    public SwaggerUiConfigParameters swaggerUiConfigParameters() {
        return new SwaggerUiConfigParameters(new SwaggerUiConfigProperties());
    }

}
