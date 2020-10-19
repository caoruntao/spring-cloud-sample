package com.crt.springcloudserviceconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringCloudServiceConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceConsumerApplication.class, args);
	}

}
