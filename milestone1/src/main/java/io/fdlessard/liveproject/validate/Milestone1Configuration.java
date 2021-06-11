package io.fdlessard.liveproject.validate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Milestone1Configuration {

    @Value("${business.logic.server.url}")
    private String url;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("metric_route", r -> r.path("/metric/**")
                        .uri(url + "/metric"))
                .route("profile_route", r -> r.path("/profile/**")
                        .uri(url + "/profile"))
                .route("advice_route", r -> r.path("/advice/**")
                        .uri(url + "/advice"))
                .build();
    }
}
