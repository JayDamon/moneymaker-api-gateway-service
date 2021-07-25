//package com.factotum.gateway.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class LocalHostConfig {
//
//    @Bean
//    public RouteLocator localHostRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/v1/accounts/**")
//                        .uri("lb://moneymaker-account-service/"))
//                .route(r -> r.path("/v1/transactions/**")
//                        .uri("lb://moneymaker-transaction-service/"))
//                .route(r -> r.path("/v1/budgets/**")
//                        .uri("lb://moneymaker-budget-service/"))
//                .build();
//    }
//
//}
