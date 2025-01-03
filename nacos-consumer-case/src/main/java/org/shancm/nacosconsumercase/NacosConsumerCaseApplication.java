package org.shancm.nacosconsumercase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerCaseApplication {

    @RestController
    public class NacosController{

        private final LoadBalancerClient loadBalancerClient;
        private final RestTemplate restTemplate;

        @Value("${spring.application.name}")
        private String appName;

        public NacosController(LoadBalancerClient loadBalancerClient, RestTemplate restTemplate) {
            this.loadBalancerClient = loadBalancerClient;
            this.restTemplate = restTemplate;
        }

        @GetMapping("/echo/app-name")
        public String echoAppName(){
            //Access through the combination of LoadBalanceClient and RestTemplate
            ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
            String path = String.format("http://%s:%s/echo/%s",serviceInstance.getHost(),serviceInstance.getPort(),appName);
            System.out.println("request path:" +path);
            return restTemplate.getForObject(path,String.class);
        }

        @GetMapping("/config/echo/app-name")
        public String echoConfig(){
            //Access through the combination of LoadBalanceClient and RestTemplate
            ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
            String path = String.format("http://%s:%s/config/echo/%s",serviceInstance.getHost(),serviceInstance.getPort(),appName);
            System.out.println("request path:" +path);
            return restTemplate.getForObject(path,String.class);
        }

    }

    //Instantiate RestTemplate Instance
    @Bean
//    @LoadBalanced
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerCaseApplication.class, args);
    }

}
