package com.scb.interview.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route for OIDC Authentication
                .route("oidc-flow", r -> r.path("/realms/master/**")
                        .uri("http://localhost:8080"))

                // Route for Backend Microservice
                .route("m1-microservice", r -> r.path("/m1/**")
                        .filters(f -> f.stripPrefix(1)) // Remove /m1 prefix
                        .uri("http://localhost:8082"))

                // Route for Backend Microservice
                .route("m2-microservice", r -> r.path("/m2/**")
                        .filters(f -> f.stripPrefix(1)) // Remove /m2 prefix
                        .uri("http://localhost:8084"))

                .build();
    }
}