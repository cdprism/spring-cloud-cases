package org.shancm.hellocloudcase.controller;

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

	@GetMapping("/hello")
	public String hello(){
		return "hello cloud";
	}
}
