package com.global.book.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

@Configuration
public class SwaggerConfig {
// if we have different package  --moduler

      @Bean
    public GroupedOpenApi userManagementApi() {
        String [] packagesToScan= {"com.service.userManagement"};
        return GroupedOpenApi.builder().group("user-management-api").
                packagesToScan(packagesToScan).
                addOperationCustomizer(appTokenHeaderParmeter())
                .build();
    }

    @Bean
    public  GroupedOpenApi bookApi(){
        String [] packagesToScan= {"com.global.book"};
        return GroupedOpenApi.builder().group("book-api").
                packagesToScan(packagesToScan).
                addOperationCustomizer(appTokenHeaderParmeter())
                .build();
    }

    @Bean
    public OperationCustomizer appTokenHeaderParmeter(){
        return (Operation operation, HandlerMethod handlerMethod) -> {
            Parameter header = new Parameter().in(ParameterIn.HEADER.toString()).required(false).
                    schema(new StringSchema()._default("app_Token_header_default_value")).name("app_token_header").description("gg");
           operation.addParametersItem(header);
           return operation;

        };
    }
}
