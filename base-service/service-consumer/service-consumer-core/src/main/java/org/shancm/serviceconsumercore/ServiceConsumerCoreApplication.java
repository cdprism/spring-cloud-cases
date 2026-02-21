package org.shancm.serviceconsumercore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author shancm
 * @since 2026-2-21 16:17:13
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceConsumerCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerCoreApplication.class, args);
    }

}
