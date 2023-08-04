package com.box.documentsuploader.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Documents Uploader API",
                version = "1.0",
                description = "Documentation for endpoints in Document Uploader API")
)
public class OpenApiConfig {
}

