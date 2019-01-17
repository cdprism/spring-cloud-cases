package org.shancm.eurekacase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author shancm
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaCaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaCaseApplication.class, args);
	}
}
