package com.crt.spring.cloud.ribbon.service.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author crt
 * @date 2020/9/14 3:12 下午
 */
@Service
public class RibbonSayingService {
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    public ResponseEntity<String> ribbonSay(String message){
        return null;
    }
}
