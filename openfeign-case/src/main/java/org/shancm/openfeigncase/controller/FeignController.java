package org.shancm.openfeigncase.controller;

import org.shancm.openfeigncase.EchoFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2023/11/11
 * @Created by Shancm
 * @Description TODO
 */

@RestController

public class FeignController {

    @Autowired
    EchoFeignService echoFeignService;


    @RequestMapping("/83echo")
    public void echo83(){
        echoFeignService.echo("83");
    }


}
