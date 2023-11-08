package com.insta.instagram.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI().info(
                new Info()
                        .title("Twitter Clone API")
                        .version("v1")
                        .description("This API enables user management, post creation, liking, commenting, and follow/unfollow functionality for the Twitter clone application. Created by **Krish Lakhani & Yash Sojitra.**")
                        .termsOfService("https://springdoc.org/")
                        .license(
                                new License()
                                        .name("Source Code")
                                        .url("https://github.com/Krish-Lakhani/Twitter")
                        )
        );
    }

}