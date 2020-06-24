package org.shancm.feigndemo.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shancm
 * @date 2020/6/23
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
