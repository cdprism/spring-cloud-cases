package org.shancm.gatewaycase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayCaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayCaseApplication.class, args);
    }

}
