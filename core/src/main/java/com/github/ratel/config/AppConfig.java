package com.github.ratel.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.github.ratel.repositories")
@ComponentScan(basePackages = "com.github.ratel")
@EntityScan(basePackages = "com.github.ratel.entity")
public class AppConfig {
}
