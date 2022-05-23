package com.hardware.api.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig
{
    @Bean
    public OpenAPI hardwareMLW()
    {
        return new OpenAPI().info(new Info()
        .title("API do Projeto HardwareMLW")
        .description("Esta API é utilizada na disciplina Desenvolvimento para Servidores-II")
        .version("v0.0.1")
        .contact(new Contact()
        .name("Lucas Celestino Alves Pereira").email("lucascap899@gmail.com"))
        .license(new License()
        .name("Apache 2.0").url("http://springdoc.org")));
    }
}
