package org.shancm.feigncase.controller;

import org.shancm.common.entity.CommonResponse;
import org.shancm.feigncase.config.FeignConfig;
import org.shancm.feigncase.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shancm
 * @package org.shancm.feigndemo.controller
 * @description:
 * @date 2019/1/18
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

	private final HelloService helloService;
	private FeignConfig feignConfig;

	@Autowired
	public FeignController(HelloService helloService, FeignConfig feignConfig) {
		this.helloService = helloService;
		this.feignConfig = feignConfig;
	}

	@GetMapping("/hi")
	public CommonResponse<Integer> hi(){
		System.out.println(feignConfig.level());
		return CommonResponse.success(helloService.hello());
	}

	@GetMapping("/timeout")
	public CommonResponse<Integer> timeout(){
		return CommonResponse.success(helloService.timeout());
	}
}
