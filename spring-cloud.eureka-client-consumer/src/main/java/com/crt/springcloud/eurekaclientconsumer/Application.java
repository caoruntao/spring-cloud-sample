package com.crt.springcloud.eurekaclientconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//@LoadBalanced 会将Bean注册到LoadBalancerInterceptor(ClientHttpRequestInterceptor)中
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(){
		//指定实现(Spring实现/Http Client/OkHttp)
		//Http Client(ClientHttpRequestFactory Http适配工厂)
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}

}
