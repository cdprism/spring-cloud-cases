package org.shancm.openfeigncase;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Date 2023/11/11
 * @Created by Shancm
 * @Description TODO
 */

@FeignClient(name = "nacos-producer")
public interface EchoFeignService {

    @RequestMapping("/echo/{string}")
    String echo(@PathVariable String string);
}
