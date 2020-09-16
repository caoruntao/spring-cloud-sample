package com.crt.spring.cloud.ribbon;

import com.crt.spring.cloud.ribbon.annotation.DiyLoadBalanced;
import com.crt.spring.cloud.ribbon.annotation.EnableDiyFeignClients;
import com.crt.spring.cloud.ribbon.interceptor.RestInterceptor;
import com.crt.spring.cloud.ribbon.service.feign.DiyFeignSayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableDiyFeignClients(clients = {DiyFeignSayingService.class})
@EnableScheduling
public class SpringCloudRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudRibbonApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	@DiyLoadBalanced
	public RestTemplate diyRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public RestInterceptor restInterceptor(){
		return new RestInterceptor();
	}

	@Bean
	@Autowired
	public Object customizer(@DiyLoadBalanced Collection<RestTemplate> restTemplates,
						   RestInterceptor interceptor){
		restTemplates.forEach(restTemplate -> {
			restTemplate.setInterceptors(Arrays.asList(interceptor));
		});
		return new Object();
	}
}
