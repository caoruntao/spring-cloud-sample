package com.crt.spring.cloud.ribbon.controller;

import com.crt.spring.cloud.ribbon.annotation.DiyLoadBalanced;
import com.crt.spring.cloud.ribbon.service.feign.DiyFeignSayingService;
import com.crt.spring.cloud.ribbon.service.feign.SayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author crt
 * @date 2020/9/14 10:29 上午
 */
@RestController
public class SayController {
    @Autowired
    private SayingService sayingService;
    @Autowired
    private DiyFeignSayingService diyFeignSayingService;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;
    @Autowired
    @DiyLoadBalanced
    private RestTemplate diyRestTemplate;

    @GetMapping("/feign/say")
    public String feignSay(String message){
        return sayingService.say(message);
    }

    @GetMapping("/diy/feign/say")
    public String diyFeignSay(String message){
        return diyFeignSayingService.say(message);
    }


    @GetMapping("/ribbon/{serviceName}/say")
    public String ribbonSay(@PathVariable String serviceName,
                            @RequestParam String message){
        String format = String.format("/%s/hystrix/say?message=%s", serviceName, message);
        return restTemplate.getForObject(format, String.class);
    }

    @GetMapping("/diy/{serviceName}/say")
    public String diySay(@PathVariable String serviceName,
                         @RequestParam String message){
        String format = String.format("/%s/hystrix/say?message=%s", serviceName, message);
        return diyRestTemplate.getForObject(format, String.class);
    }
}
