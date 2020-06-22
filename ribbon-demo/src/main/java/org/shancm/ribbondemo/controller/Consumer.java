package org.shancm.ribbondemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

	@Autowired
	public Consumer(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("/hi")
	public String hi (){
		ResponseEntity<String> entity = restTemplate.getForEntity("http://hello-service/hello/hello", String.class);
		System.out.println(entity.getHeaders().getHost());
		return entity.getBody();
	}
}
