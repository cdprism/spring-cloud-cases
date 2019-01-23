package org.shancm.feigndemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shancm
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignDemoApplication.class, args);
	}

}

