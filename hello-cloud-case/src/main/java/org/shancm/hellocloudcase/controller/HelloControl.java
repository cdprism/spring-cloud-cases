package org.shancm.hellocloudcase.controller;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.CloudEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shancm
 * @package org.shancm.hellocloudcase.controller
 * @description:
 * @date 2018/11/14
 */
@RestController
public class HelloControl {

//	private EurekaDiscoveryClient client;

//	@Autowired
//	public HelloControl(EurekaDiscoveryClient client){
//		this.client = client;
//	}


	@GetMapping("/hello")
	public String hello(){
		System.out.println("hello cloud case");
		return "hello cloud";
	}
}
