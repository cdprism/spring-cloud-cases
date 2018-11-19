package org.shancm.microweatherbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author shancm
 */
@SpringBootApplication
@EnableEurekaClient
public class MicroWeatherBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroWeatherBasicApplication.class, args);
	}
}
