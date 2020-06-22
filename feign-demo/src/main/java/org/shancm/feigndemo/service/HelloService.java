package org.shancm.feigndemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author shancm
 * @package org.shancm.feigndemo
 * @description:
 * @date 2019/1/18
 */
@FeignClient(name = "hello-service")
@Component
public interface HelloService {

	@GetMapping("/hello")
	String hello();
}