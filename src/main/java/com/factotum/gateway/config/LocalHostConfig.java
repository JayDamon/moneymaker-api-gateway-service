package com.factotum.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalHostConfig {

    @Bean
    public RouteLocator localHostRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/v1/accounts/**", "/v1/accounts*", "/v1/account-types/**")
                        .uri("lb://SETZER"))
                .route(r -> r.path("/v1/transactions/**")
                        .uri("lb://OAKA/"))
                .route(r -> r.path("/v1/budgets/**", "/v1/budget-categories/**",
                        "/v1/budget-types/**", "/v1/frequency-types/**")
                        .uri("lb://RIN/"))
                .build();
    }

}
