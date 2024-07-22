package com.elifgenc.service.bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerBean {
    @Bean
    public OpenAPI GetOpenAPIMethod(){
        return new OpenAPI().info(new Info()
                .title("ToDo List - Elif GENÇ")
                .version("V1.0.0")
                .summary("ToDo List uygulaması Web Servisleri")
                .description("ToDo List Spring Boot")
                .contact(new Contact()
                        .name("Elif GENÇ")
                        .email("eelfgnc@gmail.com")
                        .url("https://github.com/elifgenc/ToDoList")
                )
        );
    }
}
