package org.shancm.feigndemo.controller;

import org.shancm.feigndemo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shancm
 * @package org.shancm.feigndemo.controller
 * @description:
 * @date 2019/1/18
 */
@RestController
public class FeignController {

	private final HelloService helloService;

	@Autowired
	public FeignController(HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping("/hi")
	public String hi(){
		return helloService.hello();
	}
}
