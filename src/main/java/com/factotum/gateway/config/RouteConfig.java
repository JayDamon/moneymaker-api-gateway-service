package com.factotum.gateway.config;

import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator localHostRoutes(RouteLocatorBuilder builder, TokenRelayGatewayFilterFactory filterFactory
    ) {
        return builder.routes()
                .route(r -> r.path("/v1/accounts/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://moneymaker-account-service/")
                )
                .route(r -> r.path("/v1/transactions/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://moneymaker-transaction-service/"))
                .route(r -> r.path("/v1/budgets/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://moneymaker-budget-service/"))
                .build();
    }

}
