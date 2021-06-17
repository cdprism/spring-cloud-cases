package org.shancm.nacosprovidercase.controller;

import org.shancm.nacosprovidercase.service.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shancm
 * @date 2020/6/30
 */
@RestController
@RefreshScope
@RequestMapping("/config")
public class ConfigController {

    private final ServerConfig serverConfig;

    @Autowired
    public ConfigController(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Value("${from}")
    private String fromKey;

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        System.out.println(fromKey);
        return "Hello Nacos Discovery " + string + " & port is " + serverConfig.getPort();
    }
}
