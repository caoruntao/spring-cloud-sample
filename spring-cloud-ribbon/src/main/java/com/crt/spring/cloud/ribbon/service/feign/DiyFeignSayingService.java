package com.crt.spring.cloud.ribbon.service.feign;

import com.crt.spring.cloud.ribbon.annotation.DiyFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author crt
 * @date 2020/9/16 11:17 上午
 */
@DiyFeignClient("hystrix-service-provider")
public interface DiyFeignSayingService {
    @GetMapping("/hystrix/say")
    String say(@RequestParam String message);
}
