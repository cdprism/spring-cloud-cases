package org.shancm.hystrixcase.controller;

import org.shancm.common.entity.CommonResponse;
import org.shancm.hystrixcase.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author shancm
 * @package org.shancm.hystrixcase
 * @description:
 * @date 2019/1/17
 */
@RestController
@RequestMapping("/hystrix")
public class Consumer {

	private final ConsumerService consumerService;

	@Autowired
	public Consumer(ConsumerService consumerService) {
		this.consumerService = consumerService;
	}

	@GetMapping("/hi")
	public CommonResponse<String> hi (){
		return consumerService.getPort();
	}

	@GetMapping("/timeout")
	public CommonResponse<String> timeout (){
		String timeout = consumerService.timeout();
		return CommonResponse.success(timeout);
	}
}
