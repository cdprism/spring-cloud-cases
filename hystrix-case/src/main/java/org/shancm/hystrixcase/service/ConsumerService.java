package org.shancm.hystrixcase.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.shancm.common.entity.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @author Shancm
 * @date 2020/6/23
 */
@Service
public class ConsumerService {

    private final RestTemplate restTemplate;

    @Autowired
    ConsumerService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "fail")
    public CommonResponse<String> getPort(){
        ResponseEntity<String> entity = restTemplate.getForEntity("http://hello-service/hello/hello", String.class);
        return CommonResponse.success(entity.getBody());
    }

    @GetMapping("/timeout")
    public String timeout(){
        ResponseEntity<String> entity = restTemplate.getForEntity("http://hello-service/hello/timeout", String.class);
        return entity.getBody();
    }

    public CommonResponse<String> fail(){
        return CommonResponse.error();
    }

}
