package com.github.ratel.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiEndPointsInfo())
//                .enable(true)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.github.ratel.controller"))
//                .paths(PathSelectors.any())
//                .build().pathMapping("/");
//    }
//
//    private ApiInfo apiEndPointsInfo() {
//        return new ApiInfoBuilder()
//                .title("TITLE")
//                .description("DESCRIPTION")
//                .version("VERSION")
//                .build();
//    }
}
