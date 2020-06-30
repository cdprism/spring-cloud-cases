package org.shancm.feigncase.controller;

import org.shancm.common.entity.CommonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shancm
 * @date 2020/6/30
 */
@RestController("/config")
public class ConfigController {

    @Value("${from}")
    private String fromKey;

    @GetMapping("/get_config")
    public CommonResponse<String> getConfig(){
        System.out.println(fromKey);
        return CommonResponse.success(fromKey);
    }
}
