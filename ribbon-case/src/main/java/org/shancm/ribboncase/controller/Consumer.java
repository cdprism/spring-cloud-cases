package org.shancm.ribboncase.controller;

import org.shancm.common.entity.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author shancm
 * @package org.shancm.ribbondemo
 * @description:
 * @date 2019/1/17
 */
@RestController
@RequestMapping("/ribbon")
public class Consumer {

	private final RestTemplate restTemplate;
	private final DiscoveryClient client;

	@Autowired
	public Consumer(RestTemplate restTemplate, DiscoveryClient client) {
		this.restTemplate = restTemplate;
		this.client = client;
	}

	@GetMapping("/hi")
	public CommonResponse<String> hi (){
		ResponseEntity<String> entity = restTemplate.getForEntity("http://hello-service/hello/hello", String.class);
		System.out.println(entity.getBody());
		return CommonResponse.success(entity.getBody());
	}

	@GetMapping("/client")
	public CommonResponse<DiscoveryClient> client(){
		List<String> services = client.getServices();
		List<ServiceInstance> instances = client.getInstances("HELLO-SERVICE");
		services.forEach(System.out::println);
		instances.forEach(e-> System.out.println("host: "+e.getHost()+" port: "+e.getPort()+" uri: "+e.getUri()));
		return CommonResponse.success(client);
	}
}
