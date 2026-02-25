package org.shancm.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author by shancm
 * @description OneTokenNameValueGatewayFilterFactory
 * @since 2026-02-25 9:41
 */
@Component
public class OneTokenNameValueGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory implements GatewayFilter {
    //⭐⭐⭐不需要extends AbstractNameValueGatewayFilterFactory⭐⭐⭐
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                    ServerHttpResponse response = exchange.getResponse();
//                    response.getHeaders().add("X-Request-Token", UUID.randomUUID().toString());
                }));
            }
        };
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    ServerHttpResponse response = exchange.getResponse();
                    response.getHeaders().add("X-Request-Token", UUID.randomUUID().toString());
            }));
        }
    }
}
