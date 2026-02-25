package org.shancm.gateway.config;

import org.shancm.gateway.filter.OneTokenNameValueGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RewritePathGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author by shancm
 * @description GatewayRoutesConfiguration
 * @since 2026-02-25 8:16
 */
@Configuration
public class GatewayRoutesConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, RewritePathGatewayFilterFactory rewritePathGatewayFilterFactory) {
        return builder.routes()
                // 路由1：将 /api/user/** 转发到用户服务
                /*.route("user_service", r -> r
                        .path("/api/user/**")
                        .filters(f -> f
                                .addRequestHeader("X-Request-User", "gateway")
                                .circuitBreaker(config -> config
                                        .setName("userServiceCB")
                                        .setFallbackUri("forward:/fallback/user")))
                        .uri("lb://user-service")) // 使用服务发现

                // 路由2：将 /api/order/** 转发到订单服务，并添加前缀
                .route("order_service", r -> r
                        .path("/api/order/**")
                        .filters(f -> f
                                .prefixPath("/order-api") // 添加前缀
                                .retry(config -> config
                                        .setRetries(3)
                                        .setStatuses(HttpStatus.SERVICE_UNAVAILABLE)))
                        .uri("lb://order-service"))

                // 路由3：简单重定向示例
                .route("redirect_example", r -> r
                        .path("/old-path/**")
                        .filters(f -> f.redirect(302, "http://example.com/new-path"))
                        .uri("no://op")) // uri 必须指定，但实际被 filter 覆盖*/
                .route("order", r -> r
                        .path("/api/order/**")
                        .filters(f -> f
                                .rewritePath("/api/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-route", "route")
                                .filter(new OneTokenNameValueGatewayFilterFactory())
                        )
                        .uri("lb://service-consumer"))

                .build();
    }
}