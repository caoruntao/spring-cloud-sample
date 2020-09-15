package com.crt.spring.cloud.ribbon.service.ribbon;

import com.crt.spring.cloud.ribbon.annotation.DiyLoadBalanced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author crt
 * @date 2020/9/14 11:14 上午
 */
@Service
public class DiySayingService {
    @Autowired
    @DiyLoadBalanced
    private RestTemplate restTemplate;

    public String diySay(String message){
        return null;
    }
}
