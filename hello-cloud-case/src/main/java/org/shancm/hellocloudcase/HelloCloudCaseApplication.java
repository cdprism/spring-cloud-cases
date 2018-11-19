package org.shancm.hellocloudcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author shancm
 */
@SpringBootApplication
@EnableEurekaClient
public class HelloCloudCaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloCloudCaseApplication.class, args);
	}
}
