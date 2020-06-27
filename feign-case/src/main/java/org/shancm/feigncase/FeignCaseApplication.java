package org.shancm.feigncase;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shancm
 * @date 2019/1/18
 */
@SpringCloudApplication
@EnableFeignClients
public class FeignCaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignCaseApplication.class, args);
	}

}

