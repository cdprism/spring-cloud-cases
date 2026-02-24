package org.shancm.gateway.config;

import org.springframework.cloud.gateway.config.HttpClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by shancm
 * @description GatewayConfiguration
 * @since 2026-02-25 7:17
 */
@Configuration
public class GatewayConfiguration {

    /*
    * 开启允许重定向 重定向到bing 测试用
    * */
    @Bean
    public HttpClientCustomizer followRedirectCustomizer() {
        return httpClient -> httpClient.followRedirect(true);
    }

}
