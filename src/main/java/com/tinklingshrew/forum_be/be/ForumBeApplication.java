package com.tinklingshrew.forum_be.be;

import com.tinklingshrew.forum_be.controllers.*;
import com.tinklingshrew.forum_be.mappers.*;
import com.tinklingshrew.forum_be.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;




@SpringBootApplication
@ComponentScan(basePackageClasses = { UserController.class,PostController.class,CommentController.class, //
        UserService.class,PostService.class,CommentService.class, //
        UserMapper.class, PostMapper.class, CommentMapper.class, //
        ForumBeApplication.class, //
        SwaggerConfiguration.class })
@EntityScan(basePackages = { "com.tinklingshrew.forum_be.entities"})
@EnableJpaRepositories(basePackages = { "com.tinklingshrew.forum_be.repositories" })

public class ForumBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumBeApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }


}
