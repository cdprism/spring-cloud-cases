package org.shancm.serviceconsumercore.config.configuration;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author by shancm
 * @description FeignConfiguration
 * @since 2026-02-22 13:16
 */
@Configuration
public class FeignConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        /* 选择你需要的日志级别
        Feign 提供了四种日志级别：
        - `NONE`: 不记录任何日志 (默认)。
        - `BASIC`: 仅记录请求方法、URL、响应状态码以及执行时间。
        - `HEADERS`: 记录基本信息以及请求和响应的头信息。
        - `FULL`: 记录请求和响应的所有信息，包括头、体、元数据等。*/
//        return Logger.Level.FULL;
        return Logger.Level.FULL;
    }

    /*@Bean
    Retryer retryer() {

          重试间隔 100ms
          最大重试间隔 1s。新一次重试间隔是上一次重试间隔的 1.5 倍，但不能超过最大重试间隔。
          最多重试 5 次

        return new Retryer.Default(100, SECONDS.toMillis(1), 5);
    }*/
}
