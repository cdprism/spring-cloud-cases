package org.shancm.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author by shancm
 * @description CustomGlobalFilter
 * @since 2026-02-25 8:41
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().toString();
        long start = System.currentTimeMillis();
        log.info("请求 [{}] 开始，时间：{}", uri, start);

        return chain.filter(exchange)
                //添加响应头
                .then(Mono.fromRunnable(() ->
                        exchange.getResponse()
                                .getHeaders()
                                .add("X-Request-Java", "Java")
                )
                                .doFinally((result)->{log.info("请求 [{}] 结束，耗时：{}", uri, System.currentTimeMillis() - start);})
                ).then();
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}