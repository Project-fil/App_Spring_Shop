package com.github.ratel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        servers = @Server(
                url = "http://localhost:8083/",
                description = "localhost"
        )
)
@SpringBootApplication
public class RatelApplicationRun {

    public static void main(String[] args) {
        SpringApplication.run(RatelApplicationRun.class, args);
    }

}
