package com.tour.kuma.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    /**
        경로 : http://localhost:8085/swagger-ui/index.html#/
     */
    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
        Info info = new Info()
                .title("TourKuma")
                .version(springdocVersion)
                .description("여행가이드 관련 API");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}