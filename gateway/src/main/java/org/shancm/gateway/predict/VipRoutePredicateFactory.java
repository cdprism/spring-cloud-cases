package org.shancm.gateway.predict;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author by shancm
 * @description VipRoutePredictFactory
 * @since 2026-02-23 10:27
 */

@Component
public class VipRoutePredicateFactory extends AbstractRoutePredicateFactory<VipRoutePredicateFactory.Config> {


    public static final String PARAM_KEY = "param";
    public static final String REGEXP_KEY = "value";


    public VipRoutePredicateFactory() {
        super(Config.class);
    }

    /*
    短写法参数顺序
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_KEY, REGEXP_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {

            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // localhost/search?q=haha&user=Shannon
                ServerHttpRequest request = serverWebExchange.getRequest();
                String first = request.getQueryParams().getFirst(config.getParam());
                return StringUtils.hasText(first) && first.equals(config.getValue());
            }
        };
    }

    /*
    可以配置的参数
     */
    @Getter
    @Setter
    public static class Config {

        @NotEmpty
        private String param;
        @NotEmpty
        private String value;

    }
}
