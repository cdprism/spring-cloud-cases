package org.shancm.serviceconsumercore.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author by shancm
 * @description TokenInterceptor
 * @since 2026-02-22 16:00
 */
@Component
public class TokenInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("x-token", UUID.randomUUID().toString());
    }
}
