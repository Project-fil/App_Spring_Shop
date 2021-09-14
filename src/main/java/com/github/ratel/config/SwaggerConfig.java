//package com.github.ratel.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//
//@Configuration
//@EnableOpenApi
//public class SwaggerConfig {
//
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
//}
