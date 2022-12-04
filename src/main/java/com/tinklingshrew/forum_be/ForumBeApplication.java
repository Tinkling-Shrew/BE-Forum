package com.tinklingshrew.forum_be;

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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;




@SpringBootApplication
@ComponentScan(basePackageClasses = { UserController.class,PostController.class,CommentController.class, //
        UserService.class,PostService.class,CommentService.class, //
        UserMapper.class, PostMapper.class, CommentMapper.class, //
        ForumBeApplication.class, //
        SwaggerConfiguration.class })
@EntityScan(basePackages = { "com.tinklingshrew.forum_be.entities"})
@EnableJpaRepositories(basePackages = { "com.tinklingshrew.forum_be.repositories" })
@EnableWebMvc
public class ForumBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumBeApplication.class, args);
    }

    @Bean()
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
