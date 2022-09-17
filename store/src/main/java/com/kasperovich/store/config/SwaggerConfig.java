package com.kasperovich.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

@EnableWebMvc
@EnableSwagger2
@Component
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30).select()
                .apis(RequestHandlerSelectors.basePackage("com.kasperovich.store.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo())
                .securitySchemes(newArrayList(apiKey()))
                .securityContexts(newArrayList(securityContext()))
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, newArrayList(
                        new ResponseBuilder().code("500")
                                .description("500 message").build(),
                        new ResponseBuilder().code("403")
                                .description("Forbidden!!!!!").build()
                ));
    }

    private ApiKey apiKey() {
        //return new ApiKey("Authorization", "api_key", "header");
        return new ApiKey("Authorization", "Authorization", "header");             // <<< === Create a Heaader (We are createing header named "Authorization" here)
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization",
                authorizationScopes));
    }

    @Bean
    SecurityConfiguration security() {
        //return new SecurityConfiguration("emailSecurity_client", "secret", "Spring", "emailSecurity", "apiKey", ApiKeyVehicle.HEADER, "api_key", ",");
//        return new SecurityConfiguration("emailSecurity_client", "secret", "Spring", "emailSecurity", "", ApiKeyVehicle.HEADER, "", ",");
        Map<String, Object> params = new HashMap();
        params.put("apiKey", "Bearer access token");

        return SecurityConfigurationBuilder.builder()
                .scopeSeparator(",")
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Avro Cloud api", "api", "api V1", "https://www.itcsystems.com",
                new Contact("Adam Kasperovich", "api.avrocustomer.com", "akasperovich1@gmail.com"),
                "BSUIR", "https://bsuir.by",
                Collections.emptyList());
        return apiInfo;
    }
    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
