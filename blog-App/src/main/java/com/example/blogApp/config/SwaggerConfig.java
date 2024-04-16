package com.example.blogApp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;


//@OpenAPIDefinition(
//        info = @Info(
//                title = "Blogging Application Backend",
//                description = "This project is develop by Maulik Kajavadra",
//                version = "v0.0.1",
//                termsOfService = "Terms of Service",
//                contact = @Contact(
//                        name = "Maulik",
//                        email = "mskajavadra@gmail.com",
//                        url = "https://mskajavadra.com"
//                ),
//                license = @License(
//                        name = "License of APIs",
//                        url = "API License URL"
//                )
//        ),
//        externalDocs = @ExternalDocumentation(
//                description = "Extra Documents",
//                url = "Link of External Docs"
//        )
//)
//@SecurityScheme(
//        name = "bearerScheme",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        scheme = "bearer"
//)
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        String schemeName="bearerScheme";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(schemeName))
                .components(
                        new Components()
                                .addSecuritySchemes("Authorization",
                                        new SecurityScheme()
                                                .name(schemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(getInfo())
                .externalDocs(new ExternalDocumentation()
                        .description("Extra Documents")
                        .url("Link of External Docs"));
    }
    @Bean
    public GroupedOpenApi openApi() {
        return GroupedOpenApi.builder()
                .group("default")
                .pathsToMatch("/api/**")
                .build();
    }
    private Info getInfo(){
        return new Info().title("Blogging Application Backend")
                .description("This project is develop by Tushar Malvi")
                .version("v0.0.1")
                .termsOfService("Terms of Service")
                .contact(new Contact().name("Tushar").email("malvitushar2@gmail.com").url("https://tusharmalvi.com"))
                .license(new License().name("License of APIs").url("API License URL"))
                .extensions(Collections.emptyMap());
    }
}