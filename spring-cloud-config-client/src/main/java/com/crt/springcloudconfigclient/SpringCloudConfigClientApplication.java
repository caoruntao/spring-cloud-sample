package com.crt.springcloudconfigclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class SpringCloudConfigClientApplication {
	private final ContextRefresher contextRefresher;

	@Autowired
	public SpringCloudConfigClientApplication(ContextRefresher contextRefresher) {
		this.contextRefresher = contextRefresher;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigClientApplication.class, args);
	}

	//@Async
	@Scheduled(fixedRate = 5 * 1000, initialDelay = 3 * 1000)
	public void autoRefresh(){
		System.out.println("auto refresh-------自动刷新");

		Set<String> updatePropertiesNames = contextRefresher.refresh();

		if(!updatePropertiesNames.isEmpty()){
			System.out.printf("当前配置已更新，具体属性 %s", updatePropertiesNames);
		}
	}

}
