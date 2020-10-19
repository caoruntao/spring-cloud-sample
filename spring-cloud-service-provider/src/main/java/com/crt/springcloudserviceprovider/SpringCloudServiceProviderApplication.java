package com.crt.springcloudserviceprovider;

import com.crt.springcloudserviceprovider.event.MyApplicationEvent;
import com.crt.springcloudserviceprovider.listener.MyApplicationEventListener;
import com.crt.springcloudserviceprovider.message.MySink;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan(basePackages="com.crt.springcloudserviceprovider.mapper")
@EnableDiscoveryClient
@EnableHystrix
@EnableAsync
public class SpringCloudServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceProviderApplication.class, args);
	}
}
