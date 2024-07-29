package com.lilystu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Gateway 路由配置 方式2
 */

@Configuration
public class GateWayRoutesConfig {

    @Resource
    RouteLocatorBuilder routeLocatorBuilder;

    @Bean
    public RouteLocator myRouteLocator04(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        System.out.println("---" + routeLocatorBuilder.hashCode());
        System.out.println("---" + this.routeLocatorBuilder.hashCode());
        return routes.route("member_routh03", r -> r.path("/member/get/**")
                .uri("http://localhost:10000")).build();
    }

    @Bean
    public RouteLocator myRouteLocator05(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        return routes.route("member_routh05", r -> r.path("/member/save")
                .uri("http://localhost:10000")).build();
    }
}
