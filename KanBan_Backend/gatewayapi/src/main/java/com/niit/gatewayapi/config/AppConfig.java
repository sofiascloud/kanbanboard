package com.niit.gatewayapi.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean //using defualt tomcat
    public RouteLocator getRoutes(RouteLocatorBuilder builder){
        return builder.routes()

                .route(p->p.path("/kanban/**").uri("http://localhost:9898/")) //userController
                .route(p->p.path("/task/**").uri("http://localhost:9898/")) //KanBan Board
                .route(p->p.path("/user/auth/**").uri("http://localhost:8888/")) //jwt User
                .build();
    }
}