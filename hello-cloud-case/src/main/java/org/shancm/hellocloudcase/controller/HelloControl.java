package org.shancm.hellocloudcase.controller;

import org.shancm.hellocloudcase.service.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author shancm
 * @package org.shancm.hellocloudcase.controller
 * @description:
 * @date 2018/11/14
 */
@RestController
@RequestMapping("/hello")
public class HelloControl {

    private ServerConfig serverConfig;

    @Autowired
    public HelloControl(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @GetMapping("/hello")
    public Integer hello() {
        return serverConfig.getPort();
    }

    @GetMapping("/timeout")
    public Integer timeout(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverConfig.getPort();
    }
}
