package org.shancm.openfeigncase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OpenfeignCaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignCaseApplication.class, args);
    }

}
