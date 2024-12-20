package com.factotum.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    private final String transactionServiceUri;
    private final String userServiceUri;
    private final String plaidServiceUri;
    private final String accountServiceUri;

    public RouteConfig(
            @Value("${transaction-service.url:lb://transaction-update-service/}") String transactionServiceUri,
            @Value("${user-service.url:lb://user-service/}") String userServiceUri,
            @Value("${account-link-service.url:lb://account-link-service/}") String plaidServiceUri,
            @Value("${account-service.url:lb://account-update-service/}") String accountServiceUri1) {
        this.transactionServiceUri = transactionServiceUri;
        this.userServiceUri = userServiceUri;
        this.plaidServiceUri = plaidServiceUri;
        this.accountServiceUri = accountServiceUri1;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, TokenRelayGatewayFilterFactory filterFactory
    ) {
        return builder.routes()
                .route(r -> r.path("/v1/accounts/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri(this.accountServiceUri))
                .route(r -> r.path("/v1/transactions/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri(this.transactionServiceUri))
                .route(r -> r.path("/v1/budgets/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("lb://budget-service/"))
                .route(r -> r.path("/v1/users/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri(this.userServiceUri))
                .route(r -> r.path("/v1/link/**", "/v1/item/**")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri(this.plaidServiceUri))
                .build();
    }

}
