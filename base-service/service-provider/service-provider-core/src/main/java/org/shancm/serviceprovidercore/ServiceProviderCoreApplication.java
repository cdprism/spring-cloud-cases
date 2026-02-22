package org.shancm.serviceprovidercore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shancm
 * @since 2026-2-21 16:54:25
 */
//@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceProviderCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderCoreApplication.class, args);
    }

}
