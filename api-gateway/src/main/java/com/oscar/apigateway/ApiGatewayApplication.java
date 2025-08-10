package com.oscar.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("author-ms", r -> r.path("/api/authors/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://AUTHOR-SERVICE"))
                .route("book-ms", r -> r.path("/api/books/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://BOOK-SERVICE"))
                .route("order-ms", r -> r.path("/api/orders/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://ORDER-SERVICE"))
                .route("user_ms", r -> r.path("/api/users/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://USER-SERVICE"))
                .build();
    }
    
}
