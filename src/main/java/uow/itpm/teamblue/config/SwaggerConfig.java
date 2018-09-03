package uow.itpm.teamblue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("uow.itpm.teamblue.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalOperationParameters(
                        Arrays.asList(new ParameterBuilder()
                        .name("Autherization")
                        .description("Token")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(true)
                        .build())
                );
    }

    public ApiInfo apiInfo(){
        return new ApiInfo(
                "CopyCat api",
                "Cross Platform Plagerism checker tool - TeamBlue, CSCI814 - University of Worllongong - Spring 2018",
                "API TOS",
                "Terms of service",
                new Contact("TeamBlue", "localhost:8080", ""),
                "License of API", "Api Licese URL", Collections.emptyList()
        );
    }
}
