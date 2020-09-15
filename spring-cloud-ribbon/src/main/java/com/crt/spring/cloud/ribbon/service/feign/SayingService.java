package com.crt.spring.cloud.ribbon.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author crt
 * @date 2020/9/14 10:27 上午
 */
@FeignClient("hystrix-service-provider")
public interface SayingService {
    @GetMapping("/hystrix/say")
    String say(@RequestParam String message);
}
