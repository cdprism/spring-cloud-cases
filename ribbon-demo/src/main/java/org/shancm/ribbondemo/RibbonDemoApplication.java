package org.shancm.ribbondemo;

import org.shancm.ribbonrule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author shancm
 * @date 2019/1/17
 */
@SpringCloudApplication
@EnableEurekaClient
@RibbonClient(name = "hello-service", configuration = MyRule.class)
public class RibbonDemoApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonDemoApplication.class, args);
	}

}