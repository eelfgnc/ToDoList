package com.elifgenc.service.bean;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerBean {
    final String securitySchemeName = "bearerAuth";
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                    .info(new Info()
                    .title("ToDo List App Service")
                    .version("1.0")
                    .description("ToDo List API açıklama")
                    .termsOfService("http://swagger.io/terms/")
                    .license(new License()
                            .name("Apache 2.0")
                            .url("http://springdoc.org")
                    )
                    .contact(new Contact()
                            .email("eelfgnc@gmail.com")
                            .name("Elif GENÇ")
                            .url("https://github.com/eelfgnc")
                    )
                );
    }

}
