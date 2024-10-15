package com.global.book.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    private static final String SECURTY_SCHEMA_NAME = "Bearer onAuth token";

    @Bean
    public OpenAPI customeOpenApi(@Value("${application-description}") String appDescription ,
                                  @Value("${application-version}") String appVersion) {
        return  new OpenAPI().info(new Info()
                .title("sample app api")
                 .contact(getContact())
                .version(appVersion).
                description(appDescription).termsOfService("https://swagger.io/terms")
                .license(getLicense())
        ).addSecurityItem(new SecurityRequirement().addList(SECURTY_SCHEMA_NAME, Arrays.asList("read", "write"))).
                components(new Components().addSecuritySchemes(SECURTY_SCHEMA_NAME, new SecurityScheme().
                        type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));


    }

    private Contact getContact(){
     Contact contact = new Contact();
     contact.setName("auther service");
     contact.setEmail("hanmam@gmail.com");
     contact.setUrl("https://www.book.com");
     contact.setExtensions(Collections.emptyMap());
     return contact;
    }

    private License getLicense(){
        License license = new License();
        license.setName("Apache 2.0");
        license.setUrl("http://springdoc.org");
        return license;
    }
}
